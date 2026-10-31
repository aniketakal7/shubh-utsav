package com.shubhutsav.app.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object DevotionalVideoRepository {

    const val DEFAULT_FEED_URL =
        "https://raw.githubusercontent.com/aniketakal7/shubh-utsav/main/devotional_videos.json"

    val defaultChannel = DevotionalChannel(
        nameEn = "Shree Ekdanta",
        nameHi = "श्री एकदंत (Shree Ekdanta)",
        nameMr = "श्री एकदंत (Shree Ekdanta)",
        handle = "@ShreeEkdanta",
        channelUrl = "https://www.youtube.com/@ShreeEkdanta",
        subscriberText = "Official Devotional Channel",
        descriptionEn = "Official Shree Ekdanta devotional channel — Divine Abhangs, Bhajans, Tabla rhythms and sacred spiritual recitations.",
        descriptionHi = "श्री एकदंत आधिकारिक चैनल — भक्तिमय अभंग, भजन, पावन संकीर्तन, तबला वादन एवं आध्यात्मिक दर्शन।",
        descriptionMr = "श्री एकदंत अधिकृत चॅनेल — भक्तिमय अभंग, भजने, गवळणी, तबला वादन व पावन उत्सव दर्शन."
    )

    val defaultVideos = listOf(
        DevotionalVideo(
            id = "Vj_0y0u_V48",
            titleEn = "Shri Ganesh Aarti — Sukh Karta Dukh Harta",
            titleHi = "श्री गणेश आरती — सुख करता दुख हर्ता",
            titleMr = "श्री गणेश आरती — सुखकर्ता दुःखहर्ता",
            descriptionEn = "Traditional Ganpati Aarti with clear devotional recitation and divine visuals.",
            descriptionHi = "पारंपरिक गणपति बप्पा की आरती भक्तिमय स्वर व दिव्य दर्शन सहित।",
            descriptionMr = "पारंपरिक सुखकर्ता दुःखहर्ता आरती दिव्य दर्शन आणि भक्तीमय सुरांमध्ये.",
            category = "Aarti",
            duration = "4:32",
            isFeatured = true
        ),
        DevotionalVideo(
            id = "AETFvQonfV8",
            titleEn = "Shri Hanuman Chalisa — Divine Recitation",
            titleHi = "श्री हनुमान चालीसा — पावन पाठ एवं फलश्रुति",
            titleMr = "श्री हनुमान चालीसा — पावन पाठ",
            descriptionEn = "Powerful recitation of Goswami Tulsidas's Hanuman Chalisa for strength and peace.",
            descriptionHi = "संकट निवारण और आत्मबल देने वाली संपूर्ण श्री हनुमान चालीसा।",
            descriptionMr = "संकट निवारण व शक्ती देणारे संपूर्ण श्री हनुमान चालीसा पठण.",
            category = "Chalisa",
            duration = "9:45",
            isFeatured = true
        ),
        DevotionalVideo(
            id = "xZ93g7E-v5w",
            titleEn = "Maha Mrityunjaya Mantra — 108 Sacred Chants",
            titleHi = "महामृत्युंजय मंत्र — 108 पावन जाप",
            titleMr = "महामृत्युंजय मंत्र — १०८ पावन जप",
            descriptionEn = "Rudra chanting of Maha Mrityunjaya Mantra for protection, healing and health.",
            descriptionHi = "आरोग्य, दीर्घायु और सर्व संकट निवारण हेतु 108 महामृत्युंजय मंत्र जाप।",
            descriptionMr = "आरोग्य, दीर्घायुष्य आणि शांतीसाठी १०८ महामृत्युंजय मंत्र जप.",
            category = "Mantra",
            duration = "21:14",
            isFeatured = false
        ),
        DevotionalVideo(
            id = "Pj4tYfGgE-o",
            titleEn = "Shiva Tandava Stotram — Powerful Sacred Vibration",
            titleHi = "शिव तांडव स्तोत्रम् — दिव्य शिव स्तुति",
            titleMr = "शिव तांडव स्तोत्रम् — दिव्य शिव स्तुती",
            descriptionEn = "Magnificent Shiva Tandava Stotram with lyrics and meaning for inner strength.",
            descriptionHi = "रावण विरचित दिव्य शिव तांडव स्तोत्र, आत्मिक ऊर्जा व शिव कृपा प्राप्ति हेतु।",
            descriptionMr = "रावण रचित दिव्य शिव तांडव स्तोत्र, आत्मिक ऊर्जा व शिव कृपेसाठी.",
            category = "Stotra",
            duration = "10:15",
            isFeatured = true
        ),
        DevotionalVideo(
            id = "O7dI6R2E3_A",
            titleEn = "Durga Mata Aarti — Jai Ambe Gauri",
            titleHi = "माँ दुर्गा आरती — जय अम्बे गौरी",
            titleMr = "आई दुर्गा आरती — जय अंबे गौरी",
            descriptionEn = "Navratri and daily evening prayer for Goddess Durga with sweet temple melody.",
            descriptionHi = "माता रानी की प्रसिद्ध आरती 'जय अम्बे गौरी' भक्ति रस से परिपूर्ण।",
            descriptionMr = "दुर्गा मातेची प्रसिद्ध आरती 'जय अंबे गौरी' भक्तीभावाने परिपूर्ण.",
            category = "Aarti",
            duration = "5:40",
            isFeatured = false
        ),
        DevotionalVideo(
            id = "9PZ9c4n1X8A",
            titleEn = "Home Puja Vidhi — Step by Step Authentic Guide",
            titleHi = "दैनिक गृह पूजा विधि — सरल एवं शास्त्रसम्मत मार्गदर्शन",
            titleMr = "दैनिक घरगुती पूजा विधी — सोपी व शास्त्रशुद्ध पद्धत",
            descriptionEn = "Learn simple, complete Vedic daily puja rituals step-by-step for peaceful living.",
            descriptionHi = "घर में दैनिक पूजा, दीपक प्रज्वलन व आचमन की सही शास्त्रसम्मत विधि।",
            descriptionMr = "घरामध्ये दैनिक पूजा, दिवा लावणे व आचमनाची शास्त्रशुद्ध सोपी विधी.",
            category = "Puja Vidhi",
            duration = "14:20",
            isFeatured = false
        )
    )

    private const val PREFS_KEY_CACHED_VIDEOS = "cached_devotional_videos_json"

    suspend fun getVideos(context: Context): List<DevotionalVideo> = withContext(Dispatchers.IO) {
        val prefs = context.getSharedPreferences("shubh_utsav_videos", Context.MODE_PRIVATE)

        // Try fetching online feed
        try {
            val url = URL(DEFAULT_FEED_URL)
            val connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = 5000
                readTimeout = 5000
                setRequestProperty("Accept", "application/json")
            }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val jsonString = BufferedReader(InputStreamReader(connection.inputStream)).use { it.readText() }
                val parsed = parseVideos(jsonString)
                if (parsed.isNotEmpty()) {
                    prefs.edit().putString(PREFS_KEY_CACHED_VIDEOS, jsonString).apply()
                    return@withContext parsed
                }
            }
        } catch (_: Exception) {
            // Network failure: fall back to cache or defaults
        }

        // Try reading cached JSON
        val cached = prefs.getString(PREFS_KEY_CACHED_VIDEOS, null)
        if (!cached.isNullOrBlank()) {
            try {
                val parsed = parseVideos(cached)
                if (parsed.isNotEmpty()) return@withContext parsed
            } catch (_: Exception) {}
        }

        // Return defaults
        defaultVideos
    }

    suspend fun getChannelInfo(context: Context): DevotionalChannel = withContext(Dispatchers.IO) {
        val prefs = context.getSharedPreferences("shubh_utsav_videos", Context.MODE_PRIVATE)
        val cached = prefs.getString(PREFS_KEY_CACHED_VIDEOS, null)
        if (!cached.isNullOrBlank()) {
            try {
                val json = JSONObject(cached)
                if (json.has("channel")) {
                    val c = json.getJSONObject("channel")
                    return@withContext DevotionalChannel(
                        nameEn = c.optString("nameEn", defaultChannel.nameEn),
                        nameHi = c.optString("nameHi", defaultChannel.nameHi),
                        nameMr = c.optString("nameMr", defaultChannel.nameMr),
                        handle = c.optString("handle", defaultChannel.handle),
                        channelUrl = c.optString("channelUrl", defaultChannel.channelUrl),
                        subscriberText = c.optString("subscriberText", defaultChannel.subscriberText),
                        descriptionEn = c.optString("descriptionEn", defaultChannel.descriptionEn),
                        descriptionHi = c.optString("descriptionHi", defaultChannel.descriptionHi),
                        descriptionMr = c.optString("descriptionMr", defaultChannel.descriptionMr)
                    )
                }
            } catch (_: Exception) {}
        }
        defaultChannel
    }

    private fun parseVideos(jsonString: String): List<DevotionalVideo> {
        val list = mutableListOf<DevotionalVideo>()
        val json = JSONObject(jsonString)
        val videosArr: JSONArray = if (json.has("videos")) {
            json.getJSONArray("videos")
        } else return emptyList()

        for (i in 0 until videosArr.length()) {
            val v = videosArr.getJSONObject(i)
            list.add(
                DevotionalVideo(
                    id = v.getString("id"),
                    titleEn = v.optString("titleEn", ""),
                    titleHi = v.optString("titleHi", ""),
                    titleMr = v.optString("titleMr", ""),
                    descriptionEn = v.optString("descriptionEn", ""),
                    descriptionHi = v.optString("descriptionHi", ""),
                    descriptionMr = v.optString("descriptionMr", ""),
                    category = v.optString("category", "Aarti"),
                    duration = v.optString("duration", ""),
                    publishedDate = v.optString("publishedDate", ""),
                    isFeatured = v.optBoolean("isFeatured", false)
                )
            )
        }
        return list
    }
}
