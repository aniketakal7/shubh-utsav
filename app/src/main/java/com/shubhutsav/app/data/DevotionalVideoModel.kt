package com.shubhutsav.app.data

/**
 * Represents a devotional video from the author's YouTube channel.
 */
data class DevotionalVideo(
    val id: String,                 // YouTube Video ID (e.g. "8HkQ6j4p8U8")
    val titleEn: String,
    val titleHi: String,
    val titleMr: String,
    val descriptionEn: String = "",
    val descriptionHi: String = "",
    val descriptionMr: String = "",
    val category: String = "Aarti", // "Aarti", "Pravachan", "Katha", "Upay", "Darshan"
    val duration: String = "",
    val publishedDate: String = "",
    val isFeatured: Boolean = false
) {
    fun getTitle(lang: String): String = when (lang) {
        "mr" -> titleMr.ifBlank { titleHi.ifBlank { titleEn } }
        "hi" -> titleHi.ifBlank { titleEn }
        else -> titleEn
    }

    fun getDescription(lang: String): String = when (lang) {
        "mr" -> descriptionMr.ifBlank { descriptionHi.ifBlank { descriptionEn } }
        "hi" -> descriptionHi.ifBlank { descriptionEn }
        else -> descriptionEn
    }

    val thumbnailUrl: String
        get() = "https://img.youtube.com/vi/$id/hqdefault.jpg"
}

/**
 * Represents metadata for the author's devotional YouTube channel.
 */
data class DevotionalChannel(
    val nameEn: String,
    val nameHi: String,
    val nameMr: String,
    val handle: String,
    val channelUrl: String,
    val subscriberText: String,
    val descriptionEn: String,
    val descriptionHi: String,
    val descriptionMr: String
) {
    fun getName(lang: String): String = when (lang) {
        "mr" -> nameMr
        "hi" -> nameHi
        else -> nameEn
    }

    fun getDescription(lang: String): String = when (lang) {
        "mr" -> descriptionMr
        "hi" -> descriptionHi
        else -> descriptionEn
    }
}
