package com.shubhutsav.app.data

data class UpdateInfo(
    val latestVersionCode: Int,
    val latestVersionName: String,
    val minSupportedVersionCode: Int = 1,
    val isForceUpdate: Boolean = false,
    val titles: Map<String, String> = emptyMap(),
    val releaseNotes: Map<String, String> = emptyMap(),
    val updateUrl: String = "https://play.google.com/store/apps/details?id=com.shubhutsav.app",
    val directApkUrl: String? = null,
    val publishedDate: String = ""
) {
    val isDirectApk: Boolean
        get() = !directApkUrl.isNullOrBlank() ||
                updateUrl.endsWith(".apk", ignoreCase = true) ||
                updateUrl.contains(".apk?", ignoreCase = true)

    val downloadTargetUrl: String
        get() = if (!directApkUrl.isNullOrBlank()) directApkUrl else updateUrl
    fun getTitle(languageCode: String): String {
        return titles[languageCode]
            ?: titles["en"]
            ?: when (languageCode) {
                "mr" -> "नवीन आवृत्ती उपलब्ध आहे!"
                "hi" -> "नया संस्करण उपलब्ध है!"
                else -> "New Version Available!"
            }
    }

    fun getReleaseNotes(languageCode: String): String {
        return releaseNotes[languageCode]
            ?: releaseNotes["en"]
            ?: when (languageCode) {
                "mr" -> "• नवीन सण आणि २०२५-२०२६ चे अचूक मुहूर्त जोडले.\n• सुधारित पूजा विधी आणि आरती मार्गदर्शक.\n• वेग आणि स्थिरतेत सुधारणा."
                "hi" -> "• नए त्योहार और 2025-2026 के सटीक मुहूर्त जोड़े गए।\n• बेहतर पूजा विधि और आरती मार्गदर्शिका।\n• प्रदर्शन और स्थिरता में सुधार।"
                else -> "• Added new festival dates and accurate 2025-2026 Muhurat timings.\n• Enhanced step-by-step Puja guides and audio reminders.\n• Performance improvements and bug fixes."
            }
    }

    companion object {
        val SAMPLE_UPDATE = UpdateInfo(
            latestVersionCode = 2,
            latestVersionName = "1.1.0",
            minSupportedVersionCode = 1,
            isForceUpdate = false,
            titles = mapOf(
                "en" to "Shubh Utsav 1.1.0 Available! 🎉",
                "hi" to "शुभ उत्सव 1.1.0 उपलब्ध है! 🎉",
                "mr" to "शुभ उत्सव १.१.० उपलब्ध आहे! 🎉"
            ),
            releaseNotes = mapOf(
                "en" to "• Added Ganesh Chaturthi 2025 & Navratri Muhurats\n• Offline Vedic Panchang calculations\n• Interactive Puja steps tracker\n• Faster app load times",
                "hi" to "• गणेश चतुर्थी 2025 और नवरात्रि के नए मुहूर्त\n• ऑफलाइन वैदिक पंचांग गणना\n• इंटरैक्टिव पूजा विधि ट्रैकर\n• तीव्र और सहज अनुभव",
                "mr" to "• गणेशोत्सव २०२५ व नवरात्रौत्सव मुहूर्त समाविष्ट\n• ऑफलाइन वैदिक पंचांग गणना\n• संवादात्मक पूजा पायऱ्या ट्रॅकर\n• अधिक गतिमान व सुंदर अनुभव"
            ),
            updateUrl = "https://play.google.com/store/apps/details?id=com.shubhutsav.app",
            publishedDate = "2025-01-01"
        )
    }
}
