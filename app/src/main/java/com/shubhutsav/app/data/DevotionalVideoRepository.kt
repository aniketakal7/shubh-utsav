package com.shubhutsav.app.data

object DevotionalVideoRepository {

    const val DEFAULT_FEED_URL =
        "https://raw.githubusercontent.com/aniketakal7/shubh-utsav/main/devotional_videos.json"

    val defaultChannel = DevotionalChannel(
        nameEn = "Shree Ekdanta",
        nameHi = "श्री एकदंत (Shree Ekdanta)",
        nameMr = "श्री एकदंत (Shree Ekdanta)",
        handle = "@ShreeEkdanta",
        channelUrl = "https://m.youtube.com/@ShreeEkdanta",
        subscriberText = "Official Devotional Channel",
        descriptionEn = "Official Shree Ekdanta devotional channel — Divine Abhangs, Bhajans, Tabla rhythms and sacred spiritual recitations.",
        descriptionHi = "श्री एकदंत आधिकारिक चैनल — भक्तिमय अभंग, भजन, पावन संकीर्तन, तबला वादन एवं आध्यात्मिक दर्शन।",
        descriptionMr = "श्री एकदंत अधिकृत चॅनेल — भक्तिमय अभंग, भजने, गवळणी, तबला वादन व पावन उत्सव दर्शन."
    )

    val defaultVideos: List<DevotionalVideo> = emptyList()
}
