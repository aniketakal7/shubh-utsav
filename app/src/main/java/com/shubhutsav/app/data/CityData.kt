package com.shubhutsav.app.data

data class City(
    val id: String,
    val name: String,
    val hindiName: String,
    val state: String,
    val hindiState: String,
    val latitude: Double,
    val longitude: Double,
    val timezoneOffsetHours: Double = 5.5
)

object CityRepository {
    val cities = listOf(
        City("delhi", "New Delhi", "नई दिल्ली", "Delhi", "दिल्ली", 28.6139, 77.2090),
        City("mumbai", "Mumbai", "मुंबई", "Maharashtra", "महाराष्ट्र", 19.0760, 72.8777),
        City("bengaluru", "Bengaluru", "बेंगलुरु", "Karnataka", "कर्नाटक", 12.9716, 77.5946),
        City("kolkata", "Kolkata", "कोलकाता", "West Bengal", "पश्चिम बंगाल", 22.5726, 88.3639),
        City("chennai", "Chennai", "चेन्नई", "Tamil Nadu", "तमिलनाडु", 13.0827, 80.2707),
        City("hyderabad", "Hyderabad", "हैदराबाद", "Telangana", "तेलंगाना", 17.3850, 78.4867),
        City("ahmedabad", "Ahmedabad", "अहमदाबाद", "Gujarat", "गुजरात", 23.0225, 72.5714),
        City("pune", "Pune", "पुणे", "Maharashtra", "महाराष्ट्र", 18.5204, 73.8567),
        City("jaipur", "Jaipur", "जयपुर", "Rajasthan", "राजस्थान", 26.9124, 75.7873),
        City("lucknow", "Lucknow", "लखनऊ", "Uttar Pradesh", "उत्तर प्रदेश", 26.8467, 80.9462),
        City("varanasi", "Varanasi (Kashi)", "वाराणसी (काशी)", "Uttar Pradesh", "उत्तर प्रदेश", 25.3176, 82.9739),
        City("ayodhya", "Ayodhya", "अयोध्या", "Uttar Pradesh", "उत्तर प्रदेश", 26.7922, 82.1998),
        City("patna", "Patna", "पटना", "Bihar", "बिहार", 25.5941, 85.1376),
        City("bhopal", "Bhopal", "भोपाल", "Madhya Pradesh", "मध्य प्रदेश", 23.2599, 77.4126),
        City("chandigarh", "Chandigarh", "चंडीगढ़", "Punjab / Haryana", "पंजाब / हरियाणा", 30.7333, 76.7794),
        City("surat", "Surat", "सूरत", "Gujarat", "गुजरात", 21.1702, 72.8311),
        City("indore", "Indore", "इन्दौर", "Madhya Pradesh", "मध्य प्रदेश", 22.7196, 75.8577),
        City("haridwar", "Haridwar", "हरिद्वार", "Uttarakhand", "उत्तराखंड", 29.9457, 78.1642),
        City("ujjain", "Ujjain", "उज्जैन", "Madhya Pradesh", "मध्य प्रदेश", 23.1765, 75.7885),
        City("guwahati", "Guwahati", "गुवाहाटी", "Assam", "असम", 26.1445, 91.7362),
        City("kochi", "Kochi", "कोच्चि", "Kerala", "केरल", 9.9312, 76.2673),
        City("bhubaneswar", "Bhubaneswar", "भुवनेश्वर", "Odisha", "ओडिशा", 20.2961, 85.8245),
        City("nagpur", "Nagpur", "नागपुर", "Maharashtra", "महाराष्ट्र", 21.1458, 79.0882),
        City("amritsar", "Amritsar", "अमृतसर", "Punjab", "पंजाब", 31.6340, 74.8723),
        City("srinagar", "Srinagar", "श्रीनगर", "Jammu & Kashmir", "जम्मू और कश्मीर", 34.0837, 74.7973),
        City("nashik", "Nashik", "नाशिक", "Maharashtra", "महाराष्ट्र", 19.9975, 73.7898),
        City("kolhapur", "Kolhapur", "कोल्हापूर", "Maharashtra", "महाराष्ट्र", 16.7050, 74.2433),
        City("sambhajinagar", "Chhatrapati Sambhajinagar", "छत्रपती संभाजीनगर", "Maharashtra", "महाराष्ट्र", 19.8762, 75.3433),
        City("goa", "Goa (Panaji)", "गोवा (पणजी)", "Goa", "गोवा", 15.4909, 73.8278),
        City("ranchi", "Ranchi", "राँची", "Jharkhand", "झारखंड", 23.3441, 85.3096),
        City("raipur", "Raipur", "रायपुर", "Chhattisgarh", "छत्तीसगढ़", 21.2514, 81.6296),
        City("dehradun", "Dehradun", "देहरादून", "Uttarakhand", "उत्तराखंड", 30.3165, 78.0322),
        City("shimla", "Shimla", "शिमला", "Himachal Pradesh", "हिमाचल प्रदेश", 31.1048, 77.1734)
    )

    fun getCityById(id: String): City {
        return cities.find { it.id == id } ?: cities.first()
    }

    /**
     * Calculates geodesic distance between two points in kilometers using Haversine formula
     */
    fun calculateDistanceKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadiusKm = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return earthRadiusKm * c
    }

    /**
     * Finds the nearest Panchang calculation city to given GPS coordinates
     */
    fun findNearestCity(lat: Double, lon: Double): City {
        return cities.minByOrNull { calculateDistanceKm(lat, lon, it.latitude, it.longitude) } ?: cities.first()
    }

    /**
     * Finds nearest city and returns both the city and the distance in kilometers
     */
    fun findNearestCityWithDistance(lat: Double, lon: Double): Pair<City, Double> {
        val nearest = findNearestCity(lat, lon)
        val dist = calculateDistanceKm(lat, lon, nearest.latitude, nearest.longitude)
        return Pair(nearest, dist)
    }
}
