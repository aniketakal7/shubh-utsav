package com.shubhutsav.app.data

object MarathiTranslations {

    private val festivalNamesMr = mapOf(
        "diwali" to "दिवाळी (लक्ष्मीपूजन)",
        "dhanteras" to "धनत्रयोदशी",
        "choti_diwali" to "नरक चतुर्दशी (अभ्यंगस्नान)",
        "govardhan_puja" to "बलिप्रतिपदा / गोवर्धन पूजा",
        "bhai_dooj" to "भाऊबीज",
        "gudi_padwa" to "गुढीपाडवा (मराठी नववर्ष)",
        "ganesh_chaturthi" to "श्री गणेश चतुर्थी (गणेशोत्सव)",
        "shivratri" to "महाशिवरात्री",
        "janmashtami" to "श्रीकृष्ण जन्माष्टमी (दहीहंडी)",
        "ram_navami" to "श्रीराम नवमी",
        "navratri_sharad" to "शारदीय नवरात्र उत्सव",
        "chaitra_navratri" to "चैत्र नवरात्र",
        "dussehra" to "दसरा (विजयादशमी)",
        "makar_sankranti" to "मकर संक्रांत (तीळगूळ)",
        "raksha_bandhan" to "रक्षाबंधन / नारळी पौर्णिमा",
        "karva_chauth" to "करवा चौथ",
        "chhath_puja" to "छठ पूजा",
        "holi" to "होळी व धुलीवंदन",
        "ugadi" to "युगादी / गुढीपाडवा",
        "onam" to "ओणम",
        "lohri" to "लोहरी",
        "baisakhi" to "बैसाखी",
        "vishu" to "विशू",
        "guru_purnima" to "गुरुपौर्णिमा",
        "hanuman_jayanti" to "हनुमान जयंती",
        "mahavir_jayanti" to "महावीर जयंती",
        "buddha_purnima" to "बुद्ध पौर्णिमा",
        "eid_ul_fitr" to "ईद-उल-फितर",
        "eid_ul_adha" to "ईद-उल-अधा (बकरीद)",
        "christmas" to "नाताळ (ख्रिसमस)",
        "new_year" to "नवीन वर्ष"
    )

    fun getFestivalName(id: String, defaultHi: String): String {
        return festivalNamesMr[id] ?: defaultHi
    }

    fun getCategoryName(categoryHi: String): String {
        return when (categoryHi) {
            "दीपावली पर्व" -> "दिवाळी पर्व"
            "नवरात्रि" -> "नवरात्र उत्सव"
            "शिव पर्व" -> "शिव उत्सव"
            "कृष्ण पर्व" -> "कृष्ण उत्सव"
            "प्रांतीय" -> "प्रांतीय उत्सव"
            "प्रमुख" -> "प्रमुख उत्सव"
            else -> categoryHi
        }
    }

    fun getWeekday(dayIndex: Int): String {
        return when (dayIndex) {
            1 -> "रविवार"
            2 -> "सोमवार"
            3 -> "मंगळवार"
            4 -> "बुधवार"
            5 -> "गुरुवार"
            6 -> "शुक्रवार"
            7 -> "शनिवार"
            else -> "रविवार"
        }
    }

    fun getTithiName(tithiIndex: Int): String {
        return when (tithiIndex) {
            1, 16 -> "प्रतिपदा"
            2, 17 -> "द्वितीया"
            3, 18 -> "तृतीया"
            4, 19 -> "चतुर्थी"
            5, 20 -> "पंचमी"
            6, 21 -> "षष्ठी"
            7, 22 -> "सप्तमी"
            8, 23 -> "अष्टमी"
            9, 24 -> "नवमी"
            10, 25 -> "दशमी"
            11, 26 -> "एकादशी"
            12, 27 -> "द्वादशी"
            13, 28 -> "त्रयोदशी"
            14, 29 -> "चतुर्दशी"
            15 -> "पौर्णिमा"
            30 -> "अमावस्या"
            else -> "एकादशी"
        }
    }

    fun getPaksha(isShukla: Boolean): String {
        return if (isShukla) "शुक्ल पक्ष" else "कृष्ण पक्ष"
    }
}
