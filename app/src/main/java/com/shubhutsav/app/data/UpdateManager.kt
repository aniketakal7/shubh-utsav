package com.shubhutsav.app.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.FileProvider
import androidx.core.content.pm.PackageInfoCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

sealed interface UpdateCheckResult {
    data class UpdateAvailable(
        val info: UpdateInfo,
        val currentVersionCode: Long,
        val currentVersionName: String
    ) : UpdateCheckResult

    data class UpToDate(
        val currentVersionCode: Long,
        val currentVersionName: String
    ) : UpdateCheckResult

    data class Error(val message: String) : UpdateCheckResult
}

object UpdateManager {

    /**
     * Default remote JSON URL where version details are published.
     * Developers can replace this with their actual GitHub raw URL, Firebase hosting,
     * or custom server endpoint.
     */
    const val DEFAULT_UPDATE_URL =
        "https://raw.githubusercontent.com/aniketakal7/shubh-utsav/main/version_update.json"

    /**
     * Get currently installed app version name (e.g. "1.0.0")
     */
    fun getCurrentVersionName(context: Context): String {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "1.0.0"
        } catch (e: Exception) {
            "1.0.0"
        }
    }

    /**
     * Get currently installed app version code (e.g. 1)
     */
    fun getCurrentVersionCode(context: Context): Long {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            PackageInfoCompat.getLongVersionCode(pInfo)
        } catch (e: Exception) {
            1L
        }
    }

    /**
     * Check remote endpoint for app updates asynchronously.
     */
    suspend fun checkForUpdates(
        context: Context,
        endpointUrl: String? = null
    ): UpdateCheckResult = withContext(Dispatchers.IO) {
        val currentCode = getCurrentVersionCode(context)
        val currentName = getCurrentVersionName(context)

        val targetUrl = if (!endpointUrl.isNullOrBlank()) endpointUrl else DEFAULT_UPDATE_URL

        var connection: HttpURLConnection? = null
        try {
            val url = URL(targetUrl)
            connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = 7000
                readTimeout = 7000
                setRequestProperty("Accept", "application/json")
                setRequestProperty("User-Agent", "ShubhUtsavAndroid/${currentName}")
            }

            val responseCode = connection.responseCode
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return@withContext UpdateCheckResult.Error("HTTP error: $responseCode")
            }

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val jsonString = reader.use { it.readText() }
            val json = JSONObject(jsonString)

            val latestCode = json.optInt("latestVersionCode", currentCode.toInt())
            val latestName = json.optString("latestVersionName", currentName)
            val minSupported = json.optInt("minSupportedVersionCode", 1)
            val isForceExplicit = json.optBoolean("isForceUpdate", false)
            val updateUrl = json.optString(
                "updateUrl",
                "https://play.google.com/store/apps/details?id=${context.packageName}"
            )
            val directApkUrl = if (json.has("directApkUrl") && !json.isNull("directApkUrl")) {
                json.optString("directApkUrl").takeIf { it.isNotBlank() }
            } else null
            val publishedDate = json.optString("publishedDate", "")

            // Parse multilingual titles
            val titlesMap = mutableMapOf<String, String>()
            if (json.has("titles")) {
                val titlesObj = json.optJSONObject("titles")
                titlesObj?.keys()?.forEach { key ->
                    titlesMap[key] = titlesObj.getString(key)
                }
            } else if (json.has("title")) {
                titlesMap["en"] = json.getString("title")
            }

            // Parse multilingual release notes
            val releaseNotesMap = mutableMapOf<String, String>()
            if (json.has("releaseNotes")) {
                val notesObj = json.optJSONObject("releaseNotes")
                if (notesObj != null) {
                    notesObj.keys()?.forEach { key ->
                        releaseNotesMap[key] = notesObj.getString(key)
                    }
                } else {
                    releaseNotesMap["en"] = json.optString("releaseNotes", "")
                }
            }

            val isMandatory = isForceExplicit || (currentCode < minSupported)

            val info = UpdateInfo(
                latestVersionCode = latestCode,
                latestVersionName = latestName,
                minSupportedVersionCode = minSupported,
                isForceUpdate = isMandatory,
                titles = titlesMap,
                releaseNotes = releaseNotesMap,
                updateUrl = updateUrl,
                directApkUrl = directApkUrl,
                publishedDate = publishedDate
            )

            if (latestCode > currentCode) {
                UpdateCheckResult.UpdateAvailable(
                    info = info,
                    currentVersionCode = currentCode,
                    currentVersionName = currentName
                )
            } else {
                UpdateCheckResult.UpToDate(
                    currentVersionCode = currentCode,
                    currentVersionName = currentName
                )
            }
        } catch (e: Exception) {
            UpdateCheckResult.Error(e.localizedMessage ?: "Failed to check for updates")
        } finally {
            connection?.disconnect()
        }
    }

    /**
     * Open the update destination: Google Play Store app directly, or fallback to browser.
     */
    fun openUpdateUrl(context: Context, urlString: String) {
        val packageName = context.packageName

        // If it's a Play Store link, first try market:// scheme
        if (urlString.contains("play.google.com/store/apps/details")) {
            try {
                val marketIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")).apply {
                    setPackage("com.android.vending")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(marketIntent)
                return
            } catch (e: ActivityNotFoundException) {
                // Play Store app not available, continue to browser fallback
            }
        }

        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Safe fallback
        }
    }

    /**
     * Check if app has permission to install unknown apps (Android 8.0+)
     */
    fun canInstallPackages(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.packageManager.canRequestPackageInstalls()
        } else {
            true
        }
    }

    /**
     * Open system settings for allowing Shubh Utsav to install APKs
     */
    fun openInstallPermissionSettings(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val intent = Intent(
                    Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                    Uri.parse("package:${context.packageName}")
                ).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                // Fallback to general security settings
                val fallbackIntent = Intent(Settings.ACTION_SECURITY_SETTINGS).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(fallbackIntent)
            }
        }
    }

    /**
     * Download the APK file with real-time progress, then prompt the Android installer.
     */
    suspend fun downloadAndInstallApk(
        context: Context,
        apkUrl: String,
        onProgress: (Float) -> Unit
    ): Result<Unit> = withContext(Dispatchers.IO) {
        var connection: HttpURLConnection? = null
        try {
            var currentUrl = apkUrl
            var redirects = 0
            // Follow HTTP redirects (GitHub Releases redirect to objects.githubusercontent.com)
            while (redirects < 6) {
                val url = URL(currentUrl)
                connection = (url.openConnection() as HttpURLConnection).apply {
                    instanceFollowRedirects = true
                    connectTimeout = 15000
                    readTimeout = 25000
                    setRequestProperty("User-Agent", "ShubhUtsavAndroidApp")
                }
                val code = connection.responseCode
                if (code == HttpURLConnection.HTTP_MOVED_PERM ||
                    code == HttpURLConnection.HTTP_MOVED_TEMP ||
                    code == 307 || code == 308
                ) {
                    val location = connection.getHeaderField("Location")
                    if (!location.isNullOrBlank()) {
                        currentUrl = location
                        connection.disconnect()
                        redirects++
                        continue
                    }
                }
                break
            }

            val totalLength = connection?.contentLength?.toLong() ?: -1L
            val updateDir = File(context.getExternalFilesDir(null) ?: context.cacheDir, "updates")
            if (!updateDir.exists()) updateDir.mkdirs()
            val apkFile = File(updateDir, "shubh_utsav_update.apk")
            if (apkFile.exists()) apkFile.delete()

            val inputStream = connection?.inputStream
                ?: return@withContext Result.failure(Exception("Could not open download connection"))
            val outputStream = FileOutputStream(apkFile)

            inputStream.use { input ->
                outputStream.use { output ->
                    val buffer = ByteArray(16 * 1024)
                    var bytesRead: Int
                    var totalBytesRead = 0L

                    while (input.read(buffer).also { bytesRead = it } != -1) {
                        output.write(buffer, 0, bytesRead)
                        totalBytesRead += bytesRead
                        if (totalLength > 0) {
                            val progress = (totalBytesRead.toFloat() / totalLength.toFloat()).coerceIn(0f, 1f)
                            withContext(Dispatchers.Main) {
                                onProgress(progress)
                            }
                        }
                    }
                    output.flush()
                }
            }

            withContext(Dispatchers.Main) {
                onProgress(1f)
                installApk(context, apkFile)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            connection?.disconnect()
        }
    }

    /**
     * Trigger Android system PackageInstaller to install the downloaded APK file.
     */
    fun installApk(context: Context, apkFile: File) {
        try {
            val authority = "${context.packageName}.provider"
            val apkUri = FileProvider.getUriForFile(context, authority, apkFile)

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Check if permission was the issue
            if (!canInstallPackages(context)) {
                openInstallPermissionSettings(context)
            }
        }
    }
}
