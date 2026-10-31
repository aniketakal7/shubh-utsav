package com.shubhutsav.app.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.shubhutsav.app.ui.components.AppTopBar
import com.shubhutsav.app.ui.theme.KesariyaSaffron
import com.shubhutsav.app.ui.theme.VedicGold
import com.shubhutsav.app.ui.theme.VedicGoldDark

/**
 * Dedicated In-App Channel Viewer for @ShreeEkdanta.
 * Displays the exact, authentic interface of the YouTube channel with all its uploads,
 * shorts, and playlists, playing videos directly inside the app with zero external redirects.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DevotionalVideosScreen(
    navController: NavHostController,
    language: String,
    onLanguageChange: (String) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val context = LocalContext.current
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    var canGoBack by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var loadingProgress by remember { mutableIntStateOf(0) }
    var customView by remember { mutableStateOf<View?>(null) }
    var customViewCallback by remember { mutableStateOf<WebChromeClient.CustomViewCallback?>(null) }

    val channelUrl = "https://m.youtube.com/@ShreeEkdanta"

    // Intercept back navigation: if watching a video or navigating inside channel, step back.
    BackHandler(enabled = canGoBack || customView != null) {
        if (customView != null) {
            customViewCallback?.onCustomViewHidden()
            customView = null
        } else if (webViewRef?.canGoBack() == true) {
            webViewRef?.goBack()
        } else {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            if (customView == null) {
                AppTopBar(
                    title = when (language) {
                        "mr" -> "श्री एकदंत (@ShreeEkdanta)"
                        "hi" -> "श्री एकदंत (@ShreeEkdanta)"
                        else -> "Shree Ekdanta Channel"
                    },
                    language = language,
                    onLanguageChange = onLanguageChange,
                    onToggleLanguage = onToggleLanguage,
                    onBackClick = {
                        if (webViewRef?.canGoBack() == true) {
                            webViewRef?.goBack()
                        } else {
                            navController.popBackStack()
                        }
                    }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Fullscreen video container when video enters full screen
            if (customView != null) {
                AndroidView(
                    factory = { ctx ->
                        FrameLayout(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            setBackgroundColor(0xFF000000.toInt())
                            addView(customView)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                AndroidView(
                    factory = { ctx ->
                        WebView(ctx).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            setBackgroundColor(0xFF0F0B0C.toInt())

                            settings.apply {
                                javaScriptEnabled = true
                                domStorageEnabled = true
                                databaseEnabled = true
                                mediaPlaybackRequiresUserGesture = false
                                loadsImagesAutomatically = true
                                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                                cacheMode = WebSettings.LOAD_DEFAULT
                                setSupportZoom(true)
                                builtInZoomControls = false
                                userAgentString = "Mozilla/5.0 (Linux; Android 14; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Mobile Safari/537.36"
                            }

                            webChromeClient = object : WebChromeClient() {
                                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                    loadingProgress = newProgress
                                    isLoading = newProgress < 100
                                }

                                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                                    customView = view
                                    customViewCallback = callback
                                }

                                override fun onHideCustomView() {
                                    customView = null
                                    customViewCallback = null
                                }
                            }

                            webViewClient = object : WebViewClient() {
                                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                    isLoading = true
                                    canGoBack = view?.canGoBack() == true
                                }

                                override fun onPageFinished(view: WebView?, url: String?) {
                                    isLoading = false
                                    canGoBack = view?.canGoBack() == true
                                }

                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    request: WebResourceRequest?
                                ): Boolean {
                                    val target = request?.url?.toString() ?: ""
                                    // Play all channel videos, shorts, and playlists inside this in-app viewer
                                    return if (target.contains("youtube.com") || target.contains("youtu.be")) {
                                        false
                                    } else {
                                        true // Block external redirects
                                    }
                                }
                            }

                            loadUrl(channelUrl)
                            webViewRef = this
                        }
                    },
                    update = { view ->
                        canGoBack = view.canGoBack()
                    },
                    modifier = Modifier.fillMaxSize()
                )

                // Top Loading Progress Bar
                if (isLoading) {
                    LinearProgressIndicator(
                        progress = { loadingProgress / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .align(Alignment.TopCenter),
                        color = KesariyaSaffron,
                        trackColor = Color.Transparent
                    )
                }

                // Quick Navigation Floating Toolbar at bottom
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                    shadowElevation = 8.dp,
                    border = BorderStroke(1.dp, VedicGold.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Channel Home Reset button
                        IconButton(
                            onClick = { webViewRef?.loadUrl(channelUrl) },
                            modifier = Modifier.size(38.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Channel Home",
                                tint = KesariyaSaffron,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Refresh button
                        IconButton(
                            onClick = { webViewRef?.reload() },
                            modifier = Modifier.size(38.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reload",
                                tint = VedicGoldDark,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
