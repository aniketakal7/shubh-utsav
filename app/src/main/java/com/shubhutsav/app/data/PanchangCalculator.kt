package com.shubhutsav.app.data

import java.util.Calendar
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

    private val monthsEn = listOf(
        "Chaitra", "Vaishakha", "Jyeshtha", "Ashadha", "Shravana", "Bhadrapada",
        "Ashwin", "Kartik", "Margashirsha", "Pausha", "Magha", "Phalguna"
    )

    private val monthsHi = listOf(
        "चैत्र", "वैशाख", "ज्येष्ठ", "आषाढ़", "श्रावण", "भाद्रपद",
        "अश्विन", "कार्तिक", "मार्गशीर्ष", "पौष", "माघ", "फाल्गुन"
    )

    private val weekdaysEn = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    private val weekdaysHi = listOf("रविवार", "सोमवार", "मंगलवार", "बुधवार", "गुरुवार", "शुक्रवार", "शनिवार")

    /**
     * Calculates accurate Vedic Panchang, Sunrise, Sunset, and Muhurta timings
     * using NOAA solar equations and Meeus lunar perturbation algorithms.
     */
    fun calculateForDate(calendar: Calendar = Calendar.getInstance(), city: City): PanchangDay {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // 1..12
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 1=Sun .. 7=Sat

        // 1. Julian Day at 00:00 UT (Greenwich Midnight)
        val y = if (month <= 2) year - 1 else year
        val m = if (month <= 2) month + 12 else month
        val a = y / 100
        val b = 2 - a + a / 4
        val jd0 = (365.25 * (y + 4716)).toLong() + (30.6001 * (m + 1)).toInt() + day + b - 1524.5

        // 2. High-Precision Solar Position & NOAA Equation of Time (EoT)
        val t0 = (jd0 - 2451545.0) / 36525.0
        var l0 = (280.46646 + t0 * (36000.76983 + 0.0003032 * t0)) % 360.0
        if (l0 < 0) l0 += 360.0

        var mSun = (357.52911 + t0 * (35999.05029 - 0.0001537 * t0)) % 360.0
        if (mSun < 0) mSun += 360.0
        val mSunRad = Math.toRadians(mSun)

        val centerEq = (1.914602 - t0 * (0.004817 + 0.000014 * t0)) * sin(mSunRad) +
                (0.019993 - 0.000101 * t0) * sin(2 * mSunRad) +
                0.000289 * sin(3 * mSunRad)
        var sunTrueLon = (l0 + centerEq) % 360.0
        if (sunTrueLon < 0) sunTrueLon += 360.0

        val eps0 = 23.4392911 - t0 * (46.8150 / 3600.0)
        val epsRad = Math.toRadians(eps0)

        val sinDec = sin(epsRad) * sin(Math.toRadians(sunTrueLon))
        val decRad = asin(sinDec.coerceIn(-1.0, 1.0))

        val yTan = tan(epsRad / 2.0).pow(2)
        val l0Rad = Math.toRadians(l0)
        val eotMinutes = 4.0 * Math.toDegrees(
            yTan * sin(2 * l0Rad) - 2 * 0.016708634 * sin(mSunRad) +
            4 * 0.016708634 * yTan * sin(mSunRad) * cos(2 * l0Rad) -
            0.5 * yTan.pow(2) * sin(4 * l0Rad) -
            1.25 * (0.016708634).pow(2) * sin(2 * mSunRad)
        )

        // 3. Precise Local Sunrise and Sunset (IST)
        val standardMeridian = 82.5 // Indian Standard Time (IST) meridian (UTC+5.5)
        val solarNoonMinutes = 720.0 + (standardMeridian - city.longitude) * 4.0 - eotMinutes

        val latRad = Math.toRadians(city.latitude)
        // Standard zenith for sunrise/sunset is 90.8333° (atmospheric refraction 34' + solar semidiameter 16')
        val cosH = (cos(Math.toRadians(90.8333)) - sin(latRad) * sin(decRad)) / (cos(latRad) * cos(decRad))
        val hourAngle = when {
            cosH >= 1.0 -> 0.0
            cosH <= -1.0 -> 180.0
            else -> Math.toDegrees(acos(cosH))
        }
        val halfDayMinutes = (hourAngle / 15.0) * 60.0

        val sunriseMinutes = (solarNoonMinutes - halfDayMinutes).roundToInt()
        val sunsetMinutes = (solarNoonMinutes + halfDayMinutes).roundToInt()

        val sunriseStr = formatTimeFromMinutes(sunriseMinutes)
        val sunsetStr = formatTimeFromMinutes(sunsetMinutes)

        // 4. Udaya Tithi & Celestial Longitudes at Local Sunrise
        // Convert sunrise IST minutes to UTC hours
        val sunriseUtcMinutes = sunriseMinutes - 330.0
        val jdSunrise = jd0 + (sunriseUtcMinutes / 1440.0)
        val tSunrise = (jdSunrise - 2451545.0) / 36525.0
        val nSunrise = jdSunrise - 2451545.0

        // Sun longitude at sunrise
        var l0Sr = (280.46646 + 0.9856474 * nSunrise) % 360.0
        if (l0Sr < 0) l0Sr += 360.0
        val gSr = Math.toRadians((357.52911 + 0.9856003 * nSunrise) % 360.0)
        var sunLonSr = (l0Sr + 1.9146 * sin(gSr) + 0.020 * sin(2 * gSr)) % 360.0
        if (sunLonSr < 0) sunLonSr += 360.0

        // Moon longitude with major lunar perturbation terms (Meeus Ch. 47)
        val lMoon = (218.3164477 + 481267.88123421 * tSunrise) % 360.0
        val dMoon = (297.8501921 + 445267.1114034 * tSunrise) % 360.0
        val mSunSr = (357.5291092 + 35999.0502909 * tSunrise) % 360.0
        val mMoonSr = (134.9633964 + 477198.8675055 * tSunrise) % 360.0
        val fMoon = (93.2720950 + 483202.0175233 * tSunrise) % 360.0

        val dRad = Math.toRadians(dMoon)
        val mSunRadSr = Math.toRadians(mSunSr)
        val mMoonRadSr = Math.toRadians(mMoonSr)
        val fRad = Math.toRadians(fMoon)

        var moonLonSr = lMoon + 6.288774 * sin(mMoonRadSr) +
                1.274027 * sin(2 * dRad - mMoonRadSr) +
                0.658309 * sin(2 * dRad) +
                0.213618 * sin(2 * mMoonRadSr) -
                0.185116 * sin(mSunRadSr) -
                0.114332 * sin(2 * fRad)
        moonLonSr = (moonLonSr % 360.0 + 360.0) % 360.0

        // Tithi: Angular difference (Moon - Sun)
        var diff = (moonLonSr - sunLonSr) % 360.0
        if (diff < 0) diff += 360.0
        val tithiIndex = ((diff / 12.0).toInt() % 30).coerceIn(0, 29)
        val isShukla = tithiIndex < 15

        // Karana: Half-tithi
        val karanaNum = (diff / 6.0).toInt().coerceIn(0, 59)
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

        // 5. Lahiri Ayanamsha (Chitra Paksha) for Nirayana Sidereal Calculations
        val ayanamsha = 23.856 + (jdSunrise - 2451545.0) / 365.25 * (50.29 / 3600.0)
        val moonSidereal = (moonLonSr - ayanamsha + 360.0) % 360.0
        val sunSidereal = (sunLonSr - ayanamsha + 360.0) % 360.0

        // Nakshatra: Moon's sidereal position divided by 13° 20' (13.33333°)
        val nakshatraIndex = ((moonSidereal / (360.0 / 27.0)).toInt() % 27).coerceIn(0, 26)

        // Yoga: (Sidereal Sun + Sidereal Moon) divided by 13° 20'
        val yogaIndex = ((((sunSidereal + moonSidereal) % 360.0) / (360.0 / 27.0)).toInt() % 27).coerceIn(0, 26)

        // Hindu Month & Samvat
        val hinduMonthIdx = ((month + 8) % 12)
        val samvatYear = year + 57

        // 6. Muhurta Calculations
        val dayDuration = sunsetMinutes - sunriseMinutes
        val partDuration = dayDuration / 8.0

        // Rahu Kaal: 1/8th of daytime based on weekday
        // Sun(8), Mon(2), Tue(7), Wed(5), Thu(6), Fri(4), Sat(3)
        val rahuPart = when (dayOfWeek) {
            Calendar.SUNDAY -> 8
            Calendar.MONDAY -> 2
            Calendar.TUESDAY -> 7
            Calendar.WEDNESDAY -> 5
            Calendar.THURSDAY -> 6
            Calendar.FRIDAY -> 4
            else -> 3
        }
        val rahuStart = (sunriseMinutes + (rahuPart - 1) * partDuration).roundToInt()
        val rahuEnd = (sunriseMinutes + rahuPart * partDuration).roundToInt()
        val rahuKaalStr = "${formatTimeFromMinutes(rahuStart)} – ${formatTimeFromMinutes(rahuEnd)}"

        // Abhijit Muhurat: 8th Muhurat of the day (15 Muhurats during daytime), centered on Solar Noon
        val muhuratDuration = dayDuration / 15.0
        val abhijitStart = (solarNoonMinutes - muhuratDuration / 2.0).roundToInt()
        val abhijitEnd = (solarNoonMinutes + muhuratDuration / 2.0).roundToInt()
        val abhijitStr = "${formatTimeFromMinutes(abhijitStart)} – ${formatTimeFromMinutes(abhijitEnd)}"

        // Brahma Muhurat: 2 Muhurats (96 min to 48 min) before Sunrise
        val brahmaStart = sunriseMinutes - 96
        val brahmaEnd = sunriseMinutes - 48
        val brahmaStr = "${formatTimeFromMinutes(brahmaStart)} – ${formatTimeFromMinutes(brahmaEnd)}"

        // Amrit Kaal: Daytime auspicious Choghadiya window (never coincides with Rahu Kaal)
        // Amrit Choghadiya segment from sunrise: Sun(4), Mon(1), Tue(5), Wed(2), Thu(7), Fri(3), Sat(7)
        val amritPart = when (dayOfWeek) {
            Calendar.SUNDAY -> 4
            Calendar.MONDAY -> 1
            Calendar.TUESDAY -> 5
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 7
            Calendar.FRIDAY -> 3
            else -> 7
        }
        val amritStart = (sunriseMinutes + (amritPart - 1) * partDuration).roundToInt()
        val amritEnd = (sunriseMinutes + amritPart * partDuration).roundToInt()
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
