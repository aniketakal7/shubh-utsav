package com.shubhutsav.app.data

import java.util.Calendar
import java.util.TimeZone
import kotlin.math.*

data class PanchangDay(
    val gregorianDateEn: String,
    val gregorianDateHi: String,
    val hinduMonthEn: String,
    val hinduMonthHi: String,
    val samvatYear: Int,
    val tithiEn: String,
    val tithiHi: String,
    val pakshaEn: String,
    val pakshaHi: String,
    val nakshatraEn: String,
    val nakshatraHi: String,
    val yogaEn: String,
    val yogaHi: String,
    val karanaEn: String,
    val karanaHi: String,
    val weekdayEn: String,
    val weekdayHi: String,
    val sunrise: String,
    val sunset: String,
    val rahuKaal: String,
    val abhijitMuhurat: String,
    val brahmaMuhurat: String,
    val amritKaal: String
)

object PanchangCalculator {

    private val tithiNamesEn = listOf(
        "Pratipada", "Dwitiya", "Tritiya", "Chaturthi", "Panchami",
        "Shashti", "Saptami", "Ashtami", "Navami", "Dashami",
        "Ekadashi", "Dwadashi", "Trayodashi", "Chaturdashi", "Purnima",
        "Pratipada", "Dwitiya", "Tritiya", "Chaturthi", "Panchami",
        "Shashti", "Saptami", "Ashtami", "Navami", "Dashami",
        "Ekadashi", "Dwadashi", "Trayodashi", "Chaturdashi", "Amavasya"
    )

    private val tithiNamesHi = listOf(
        "प्रतिपदा", "द्वितीया", "तृतीया", "चतुर्थी", "पंचमी",
        "षष्ठी", "सप्तमी", "अष्टमी", "नवमी", "दशमी",
        "एकादशी", "द्वादशी", "त्रयोदशी", "चतुर्दशी", "पूर्णिमा",
        "प्रतिपदा", "द्वितीया", "तृतीया", "चतुर्थी", "पंचमी",
        "षष्ठी", "सप्तमी", "अष्टमी", "नवमी", "दशमी",
        "एकादशी", "द्वादशी", "त्रयोदशी", "चतुर्दशी", "अमावस्या"
    )

    private val nakshatraNamesEn = listOf(
        "Ashwini", "Bharani", "Krittika", "Rohini", "Mrigashirsha",
        "Ardra", "Punarvasu", "Pushya", "Ashlesha", "Magha",
        "Purva Phalguni", "Uttara Phalguni", "Hasta", "Chitra", "Swati",
        "Vishakha", "Anuradha", "Jyeshtha", "Mula", "Purva Ashadha",
        "Uttara Ashadha", "Shravana", "Dhanishta", "Shatabhisha", "Purva Bhadrapada",
        "Uttara Bhadrapada", "Revati"
    )

    private val nakshatraNamesHi = listOf(
        "अश्विनी", "भरणी", "कृत्तिका", "रोहिणी", "मृगशिरा",
        "आर्द्रा", "पुनर्वसु", "पुष्य", "आश्लेषा", "मघा",
        "पूर्वाफाल्गुनी", "उत्तराफाल्गुनी", "हस्त", "चित्रा", "स्वाती",
        "विशाखा", "अनुराधा", "ज्येष्ठा", "मूल", "पूर्वाषाढ़ा",
        "उत्तराषाढ़ा", "श्रवण", "धनिष्ठा", "शतभिषा", "पूर्वाभाद्रपद",
        "उत्तराभाद्रपद", "रेवती"
    )

    private val yogaNamesEn = listOf(
        "Vishkambha", "Priti", "Ayushman", "Saubhagya", "Shobhana",
        "Atiganda", "Sukarma", "Dhriti", "Shoola", "Ganda",
        "Vriddhi", "Dhruva", "Vyaghata", "Harshana", "Vajra",
        "Siddhi", "Vyatipata", "Variyan", "Parigha", "Shiva",
        "Siddha", "Sadhya", "Shubha", "Shukla", "Brahma",
        "Indra", "Vaidhriti"
    )

    private val yogaNamesHi = listOf(
        "विष्कम्भ", "प्रीति", "आयुष्मान", "सौभाग्य", "शोभन",
        "अतिगण्ड", "सुकर्मा", "धृति", "शूल", "गण्ड",
        "वृद्धि", "ध्रुव", "व्याघात", "हर्षण", "वज्र",
        "सिद्धि", "व्यतीपात", "वरीयान्", "परिघ", "शिव",
        "सिद्ध", "साध्य", "शुभ", "शुक्ल", "ब्रह्म",
        "इन्द्र", "वैधृति"
    )

    private val karanaNamesEn = listOf(
        "Bava", "Balava", "Kaulava", "Taitila", "Garija", "Vanija", "Vishti (Bhadra)",
        "Shakuni", "Chatushpada", "Naga", "Kimstughna"
    )

    private val karanaNamesHi = listOf(
        "बव", "बालव", "कौलव", "तैतिल", "गरिज", "वणिज", "विष्टि (भद्रा)",
        "शकुनि", "चतुष्पाद", "नाग", "किंस्तुघ्न"
    )

    private val monthsEn = listOf("Chaitra", "Vaishakha", "Jyeshtha", "Ashadha", "Shravana", "Bhadrapada", "Ashwin", "Kartik", "Margashirsha", "Pausha", "Magha", "Phalguna")
    private val monthsHi = listOf("चैत्र", "वैशाख", "ज्येष्ठ", "आषाढ़", "श्रावण", "भाद्रपद", "अश्विन", "कार्तिक", "मार्गशीर्ष", "पौष", "माघ", "फाल्गुन")

    private val weekdaysEn = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    private val weekdaysHi = listOf("रविवार", "सोमवार", "मंगलवार", "बुधवार", "गुरुवार", "शुक्रवार", "शनिवार")

    fun calculateForDate(calendar: Calendar = Calendar.getInstance(), city: City): PanchangDay {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // 1..12
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 1=Sun .. 7=Sat
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

        // Julian Day calculation
        val a = (14 - month) / 12
        val y = year + 4800 - a
        val m = month + 12 * a - 3
        val jd = day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045.0 + 0.5

        // Approximate Sun and Moon longitudes (ecliptic degrees)
        val n = jd - 2451545.0
        val L0 = (280.460 + 0.9856474 * n) % 360.0 // Sun mean longitude
        val g = Math.toRadians((357.528 + 0.9856003 * n) % 360.0) // Sun mean anomaly
        val sunLon = (L0 + 1.915 * sin(g) + 0.020 * sin(2 * g) + 360.0) % 360.0

        // Moon mean longitude and anomaly
        val moonMean = (218.316 + 13.176396 * n) % 360.0
        val moonAnomaly = Math.toRadians((134.963 + 13.064993 * n) % 360.0)
        val moonLon = (moonMean + 6.289 * sin(moonAnomaly) + 360.0) % 360.0

        // Tithi: Angular difference divided by 12 degrees
        var diff = moonLon - sunLon
        if (diff < 0) diff += 360.0
        val tithiIndex = ((diff / 12.0).toInt() % 30).coerceIn(0, 29)
        val isShukla = tithiIndex < 15

        // Nakshatra: Moon longitude divided by 13.33333 degrees
        val nakshatraIndex = ((moonLon / (360.0 / 27.0)).toInt() % 27).coerceIn(0, 26)

        // Yoga: (Sun + Moon) divided by 13.33333 degrees
        val sumLon = (sunLon + moonLon) % 360.0
        val yogaIndex = ((sumLon / (360.0 / 27.0)).toInt() % 27).coerceIn(0, 26)

        // Karana: Half-tithi
        val karanaNum = (diff / 6.0).toInt()
        val karanaIndex = if (karanaNum == 0) 10 // Kimstughna
        else if (karanaNum >= 57) {
            when (karanaNum) {
                57 -> 7 // Shakuni
                58 -> 8 // Chatushpada
                else -> 9 // Naga
            }
        } else {
            (karanaNum - 1) % 7 // Bava..Vishti
        }.coerceIn(0, 10)

        // Hindu Month & Samvat
        val hinduMonthIdx = ((month + 8) % 12)
        val samvatYear = year + 57

        // Sunrise & Sunset calculation based on latitude & longitude
        val sunDeclination = 23.45 * sin(Math.toRadians((360.0 / 365.0) * (dayOfYear - 81)))
        val latRad = Math.toRadians(city.latitude)
        val decRad = Math.toRadians(sunDeclination)

        // Hour angle at horizon (zenith = 90.833 deg)
        val cosH = (cos(Math.toRadians(90.833)) - sin(latRad) * sin(decRad)) / (cos(latRad) * cos(decRad))
        val hourAngle = Math.toDegrees(acos(cosH.coerceIn(-1.0, 1.0)))

        // Solar noon in hours (UTC+5.5)
        val standardMeridian = 82.5 // Indian Standard Time (IST) meridian
        val timeCorrectionMinutes = (standardMeridian - city.longitude) * 4.0 // 4 mins per degree
        val solarNoonMinutes = 12 * 60 + timeCorrectionMinutes
        val halfDayMinutes = (hourAngle / 15.0) * 60.0

        val sunriseMinutes = (solarNoonMinutes - halfDayMinutes).toInt()
        val sunsetMinutes = (solarNoonMinutes + halfDayMinutes).toInt()

        val sunriseStr = formatTimeFromMinutes(sunriseMinutes)
        val sunsetStr = formatTimeFromMinutes(sunsetMinutes)

        // Rahu Kaal calculation (1/8th of daytime based on weekday)
        // 1=Sun(8), 2=Mon(2), 3=Tue(7), 4=Wed(5), 5=Thu(6), 6=Fri(4), 7=Sat(3)
        val rahuPart = when (dayOfWeek) {
            Calendar.SUNDAY -> 8
            Calendar.MONDAY -> 2
            Calendar.TUESDAY -> 7
            Calendar.WEDNESDAY -> 5
            Calendar.THURSDAY -> 6
            Calendar.FRIDAY -> 4
            else -> 3
        }
        val dayDuration = sunsetMinutes - sunriseMinutes
        val partDuration = dayDuration / 8.0
        val rahuStart = (sunriseMinutes + (rahuPart - 1) * partDuration).toInt()
        val rahuEnd = (sunriseMinutes + rahuPart * partDuration).toInt()
        val rahuKaalStr = "${formatTimeFromMinutes(rahuStart)} – ${formatTimeFromMinutes(rahuEnd)}"

        // Abhijit Muhurat: Midday period (approx 24 min before and after solar noon)
        val abhijitStart = (solarNoonMinutes - 24).toInt()
        val abhijitEnd = (solarNoonMinutes + 24).toInt()
        val abhijitStr = "${formatTimeFromMinutes(abhijitStart)} – ${formatTimeFromMinutes(abhijitEnd)}"

        // Brahma Muhurat: 96 to 48 minutes before sunrise
        val brahmaStart = sunriseMinutes - 96
        val brahmaEnd = sunriseMinutes - 48
        val brahmaStr = "${formatTimeFromMinutes(brahmaStart)} – ${formatTimeFromMinutes(brahmaEnd)}"

        // Amrit Kaal (approximate auspicious window)
        val amritStart = (sunriseMinutes + 4 * partDuration).toInt()
        val amritEnd = (sunriseMinutes + 5 * partDuration).toInt()
        val amritStr = "${formatTimeFromMinutes(amritStart)} – ${formatTimeFromMinutes(amritEnd)}"

        val gregorianEn = "$day ${getEnglishMonthName(month)} $year"
        val gregorianHi = "$day ${getHindiMonthName(month)} $year"

        return PanchangDay(
            gregorianDateEn = gregorianEn,
            gregorianDateHi = gregorianHi,
            hinduMonthEn = monthsEn[hinduMonthIdx],
            hinduMonthHi = monthsHi[hinduMonthIdx],
            samvatYear = samvatYear,
            tithiEn = tithiNamesEn[tithiIndex],
            tithiHi = tithiNamesHi[tithiIndex],
            pakshaEn = if (isShukla) "Shukla Paksha" else "Krishna Paksha",
            pakshaHi = if (isShukla) "शुक्ल पक्ष" else "कृष्ण पक्ष",
            nakshatraEn = nakshatraNamesEn[nakshatraIndex],
            nakshatraHi = nakshatraNamesHi[nakshatraIndex],
            yogaEn = yogaNamesEn[yogaIndex],
            yogaHi = yogaNamesHi[yogaIndex],
            karanaEn = karanaNamesEn[karanaIndex],
            karanaHi = karanaNamesHi[karanaIndex],
            weekdayEn = weekdaysEn[dayOfWeek - 1],
            weekdayHi = weekdaysHi[dayOfWeek - 1],
            sunrise = sunriseStr,
            sunset = sunsetStr,
            rahuKaal = rahuKaalStr,
            abhijitMuhurat = abhijitStr,
            brahmaMuhurat = brahmaStr,
            amritKaal = amritStr
        )
    }

    private fun formatTimeFromMinutes(totalMinutes: Int): String {
        var m = totalMinutes % (24 * 60)
        if (m < 0) m += (24 * 60)
        val hours24 = m / 60
        val minutes = m % 60
        val isPm = hours24 >= 12
        val hours12 = when {
            hours24 == 0 -> 12
            hours24 > 12 -> hours24 - 12
            else -> hours24
        }
        val ampm = if (isPm) "PM" else "AM"
        return String.format("%02d:%02d %s", hours12, minutes, ampm)
    }

    private fun getEnglishMonthName(month: Int): String {
        return when (month) {
            1 -> "Jan"; 2 -> "Feb"; 3 -> "Mar"; 4 -> "Apr"; 5 -> "May"; 6 -> "Jun"
            7 -> "Jul"; 8 -> "Aug"; 9 -> "Sep"; 10 -> "Oct"; 11 -> "Nov"; else -> "Dec"
        }
    }

    private fun getHindiMonthName(month: Int): String {
        return when (month) {
            1 -> "जनवरी"; 2 -> "फ़रवरी"; 3 -> "मार्च"; 4 -> "अप्रैल"; 5 -> "मई"; 6 -> "जून"
            7 -> "जुलाई"; 8 -> "अगस्त"; 9 -> "सितंबर"; 10 -> "अक्टूबर"; 11 -> "नवंबर"; else -> "दिसंबर"
        }
    }
}
