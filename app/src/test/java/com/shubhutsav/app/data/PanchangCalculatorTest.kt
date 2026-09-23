package com.shubhutsav.app.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class PanchangCalculatorTest {

    @Test
    fun testPanchangFor23September2026_Delhi() {
        val delhi = City("delhi", "New Delhi", "नई दिल्ली", "Delhi", "दिल्ली", 28.6139, 77.2090)
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2026)
            set(Calendar.MONTH, Calendar.SEPTEMBER) // Calendar.SEPTEMBER is 8 (0-indexed)
            set(Calendar.DAY_OF_MONTH, 23)
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
        }

        val panchang = PanchangCalculator.calculateForDate(calendar, delhi)

        println("=== 23 September 2026 Delhi Panchang ===")
        println("Tithi: ${panchang.tithiEn} (${panchang.tithiHi})")
        println("Paksha: ${panchang.pakshaEn}")
        println("Nakshatra: ${panchang.nakshatraEn} (${panchang.nakshatraHi})")
        println("Yoga: ${panchang.yogaEn} (${panchang.yogaHi})")
        println("Sunrise: ${panchang.sunrise}")
        println("Sunset: ${panchang.sunset}")
        println("Rahu Kaal: ${panchang.rahuKaal}")
        println("Abhijit Muhurat: ${panchang.abhijitMuhurat}")
        println("Brahma Muhurat: ${panchang.brahmaMuhurat}")
        println("Amrit Kaal: ${panchang.amritKaal}")

        // 1. Tithi must be Dwadashi (not Trayodashi)
        assertEquals("Dwadashi", panchang.tithiEn)
        assertEquals("द्वादशी", panchang.tithiHi)
        assertEquals("Shukla Paksha", panchang.pakshaEn)

        // 2. Nakshatra at sunrise must be Shravana
        assertEquals("Shravana", panchang.nakshatraEn)

        // 3. Yoga must be Sukarma
        assertEquals("Sukarma", panchang.yogaEn)

        // 4. Sunrise should be 06:09 AM or 06:10 AM (within 1 minute)
        assertTrue(panchang.sunrise in listOf("06:09 AM", "06:10 AM"))

        // 5. Sunset should be 06:16 PM, 06:17 PM, or 06:18 PM (within 1 minute)
        assertTrue(panchang.sunset in listOf("06:16 PM", "06:17 PM", "06:18 PM"))

        // 6. Rahu Kaal on Wednesday (approx 12:13 PM – 01:44 PM / 12:14 PM – 01:45 PM)
        assertTrue(panchang.rahuKaal.startsWith("12:13 PM") || panchang.rahuKaal.startsWith("12:14 PM"))

        // 7. Amrit Kaal must not equal Rahu Kaal
        assertTrue(panchang.amritKaal != panchang.rahuKaal)
    }

    @Test
    fun testPanchangFor23September2026_Mumbai() {
        val mumbai = City("mumbai", "Mumbai", "मुंबई", "Maharashtra", "महाराष्ट्र", 19.0760, 72.8777)
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2026)
            set(Calendar.MONTH, Calendar.SEPTEMBER)
            set(Calendar.DAY_OF_MONTH, 23)
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
        }

        val panchang = PanchangCalculator.calculateForDate(calendar, mumbai)

        assertEquals("Dwadashi", panchang.tithiEn)
        assertEquals("Shravana", panchang.nakshatraEn)
        // Sunrise in Mumbai is later due to western longitude (~06:27 AM)
        assertTrue(panchang.sunrise in listOf("06:26 AM", "06:27 AM", "06:28 AM"))
        assertTrue(panchang.sunset in listOf("06:33 PM", "06:34 PM", "06:35 PM"))
    }
}
