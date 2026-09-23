package com.shubhutsav.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shubhutsav.app.data.CityRepository
import com.shubhutsav.app.data.FestivalRepository
import com.shubhutsav.app.data.PreferencesManager
import com.shubhutsav.app.data.UpdateCheckResult
import com.shubhutsav.app.data.UpdateInfo
import com.shubhutsav.app.data.UpdateManager
import com.shubhutsav.app.data.FirebaseInitHelper
import com.shubhutsav.app.notifications.NotificationHelper
import com.shubhutsav.app.ui.components.AppUpdateDialog
import com.shubhutsav.app.ui.screens.*
import com.shubhutsav.app.ui.theme.ShubhUtsavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize high importance notification channels
        NotificationHelper.createNotificationChannel(this)

        // Initialize Firebase services (Analytics, Crashlytics, Messaging topics)
        FirebaseInitHelper.init(this)

        enableEdgeToEdge()
        setContent {
            ShubhUtsavApp()
        }
    }
}

@Composable
fun ShubhUtsavApp() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val prefs = remember { PreferencesManager(context) }

    var language by remember { mutableStateOf(prefs.languageCode) }
    var isHindi by remember { mutableStateOf(prefs.isHindi) }
    var isDark by remember { mutableStateOf(prefs.isDarkMode) }
    var cityId by remember { mutableStateOf(prefs.cityId) }
    var isPremium by remember { mutableStateOf(prefs.isPremium) }

    var updateDialogInfo by remember { mutableStateOf<UpdateInfo?>(null) }
    val currentVersionName = remember { UpdateManager.getCurrentVersionName(context) }

    // Check for updates automatically on app launch
    LaunchedEffect(Unit) {
        val result = UpdateManager.checkForUpdates(
            context = context,
            endpointUrl = prefs.customUpdateEndpointUrl
        )
        if (result is UpdateCheckResult.UpdateAvailable) {
            // Show if it's mandatory or hasn't been dismissed by the user yet
            if (result.info.isForceUpdate || prefs.dismissedUpdateVersionCode < result.info.latestVersionCode) {
                updateDialogInfo = result.info
            }
        }
    }

    val handleLanguageChange: (String) -> Unit = { newLang ->
        language = newLang
        prefs.languageCode = newLang
        isHindi = newLang == "hi"
    }

    val handleToggleLanguage: () -> Unit = {
        val nextLang = when (language) {
            "en" -> "hi"
            "hi" -> "mr"
            else -> "en"
        }
        handleLanguageChange(nextLang)
    }

    val currentCity = remember(cityId) { CityRepository.getCityById(cityId) }
    val navController = rememberNavController()

    ShubhUtsavTheme(darkTheme = isDark) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = androidx.compose.material3.MaterialTheme.colorScheme.background
        ) {
            val startDestination = if (prefs.isOnboarded) "home" else "onboarding"

            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                // Onboarding Screen
                composable("onboarding") {
                    OnboardingScreen(
                        onComplete = { selectedHindi, selectedCityId, selectedRitualStyle ->
                            prefs.isOnboarded = true
                            prefs.isHindi = selectedHindi
                            prefs.cityId = selectedCityId
                            prefs.ritualStyle = selectedRitualStyle

                            isHindi = selectedHindi
                            cityId = selectedCityId

                            navController.navigate("home") {
                                popUpTo("onboarding") { inclusive = true }
                            }
                        },
                        onCompleteLanguage = { selectedLang, selectedCityId, selectedRitualStyle ->
                            prefs.isOnboarded = true
                            handleLanguageChange(selectedLang)
                            prefs.cityId = selectedCityId
                            prefs.ritualStyle = selectedRitualStyle

                            cityId = selectedCityId

                            navController.navigate("home") {
                                popUpTo("onboarding") { inclusive = true }
                            }
                        }
                    )
                }

                // Home Screen
                composable("home") {
                    HomeScreen(
                        navController = navController,
                        city = currentCity,
                        isHindi = isHindi,
                        language = language,
                        onLanguageChange = handleLanguageChange,
                        onToggleLanguage = handleToggleLanguage,
                        onCityChanged = { newCity ->
                            cityId = newCity.id
                            prefs.cityId = newCity.id
                        }
                    )
                }

                // Festival Calendar Screen
                composable("calendar") {
                    CalendarScreen(
                        navController = navController,
                        isHindi = isHindi,
                        language = language,
                        isPremium = isPremium,
                        onLanguageChange = handleLanguageChange,
                        onToggleLanguage = handleToggleLanguage,
                        onUpgradePremium = {
                            isPremium = true
                            prefs.isPremium = true
                        }
                    )
                }

                // Festival Detail Screen
                composable(
                    route = "festival/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { backStackEntry ->
                    val festivalId = backStackEntry.arguments?.getString("id") ?: "diwali"
                    val festival = FestivalRepository.getFestivalById(festivalId)
                        ?: FestivalRepository.festivals.first()

                    FestivalDetailScreen(
                        navController = navController,
                        festival = festival,
                        isHindi = isHindi,
                        language = language,
                        onLanguageChange = handleLanguageChange,
                        onToggleLanguage = handleToggleLanguage
                    )
                }

                // Shopping List Screen
                composable(
                    route = "shopping/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { backStackEntry ->
                    val festivalId = backStackEntry.arguments?.getString("id") ?: "diwali"
                    val festival = FestivalRepository.getFestivalById(festivalId)
                        ?: FestivalRepository.festivals.first()

                    ShoppingScreen(
                        navController = navController,
                        festival = festival,
                        isHindi = isHindi,
                        language = language,
                        onLanguageChange = handleLanguageChange,
                        onToggleLanguage = handleToggleLanguage
                    )
                }

                // Settings Screen
                composable("settings") {
                    SettingsScreen(
                        navController = navController,
                        city = currentCity,
                        isHindi = isHindi,
                        language = language,
                        isDark = isDark,
                        isPremium = isPremium,
                        onLanguageChange = handleLanguageChange,
                        onToggleLanguage = handleToggleLanguage,
                        onToggleDark = { newDark ->
                            isDark = newDark
                            prefs.isDarkMode = newDark
                        },
                        onCityChanged = { newCity ->
                            cityId = newCity.id
                            prefs.cityId = newCity.id
                        },
                        onUpgradePremium = {
                            isPremium = true
                            prefs.isPremium = true
                        },
                        onShowUpdateDialog = { info ->
                            updateDialogInfo = info
                        }
                    )
                }
            }

            // In-App Update Dialog
            if (updateDialogInfo != null) {
                AppUpdateDialog(
                    updateInfo = updateDialogInfo!!,
                    currentVersionName = currentVersionName,
                    language = language,
                    onUpdateClick = { updateUrl ->
                        UpdateManager.openUpdateUrl(context, updateUrl)
                    },
                    onDismiss = {
                        // Remember dismissed version so user isn't prompted repeatedly for optional updates
                        prefs.dismissedUpdateVersionCode = updateDialogInfo!!.latestVersionCode
                        updateDialogInfo = null
                    }
                )
            }
        }
    }
}
