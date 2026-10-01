package com.shubhutsav.app.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("shubh_utsav_prefs", Context.MODE_PRIVATE)

    var isOnboarded: Boolean
        get() = prefs.getBoolean("is_onboarded", false)
        set(value) = prefs.edit().putBoolean("is_onboarded", value).apply()

    var isHindi: Boolean
        get() = prefs.getBoolean("is_hindi", false)
        set(value) = prefs.edit().putBoolean("is_hindi", value).apply()

    var languageCode: String
        get() = prefs.getString("language_code", if (isHindi) "hi" else "en") ?: "en"
        set(value) {
            prefs.edit().putString("language_code", value).apply()
            isHindi = (value == "hi")
        }

    var cityId: String
        get() = prefs.getString("city_id", "delhi") ?: "delhi"
        set(value) = prefs.edit().putString("city_id", value).apply()

    fun getSelectedLocation(): City {
        val isCustomVillage = prefs.getBoolean("is_custom_village", false)
        if (isCustomVillage) {
            val id = prefs.getString("village_id", "custom_village") ?: "custom_village"
            val name = prefs.getString("village_name", "") ?: ""
            val hindiName = prefs.getString("village_hindi_name", name) ?: name
            val state = prefs.getString("village_state", "Maharashtra") ?: "Maharashtra"
            val hindiState = prefs.getString("village_hindi_state", state) ?: state
            val district = prefs.getString("village_district", null)
            val hindiDistrict = prefs.getString("village_hindi_district", district)
            val latBits = prefs.getLong("village_lat_bits", java.lang.Double.doubleToLongBits(19.0760))
            val lonBits = prefs.getLong("village_lon_bits", java.lang.Double.doubleToLongBits(72.8777))
            val lat = java.lang.Double.longBitsToDouble(latBits)
            val lon = java.lang.Double.longBitsToDouble(lonBits)

            if (name.isNotBlank()) {
                return City(
                    id = id,
                    name = name,
                    hindiName = hindiName,
                    state = state,
                    hindiState = hindiState,
                    latitude = lat,
                    longitude = lon,
                    isVillage = true,
                    district = district,
                    hindiDistrict = hindiDistrict
                )
            }
        }
        return CityRepository.getCityById(cityId)
    }

    fun saveSelectedLocation(city: City) {
        val editor = prefs.edit()
        if (city.isVillage) {
            editor.putBoolean("is_custom_village", true)
            editor.putString("village_id", city.id)
            editor.putString("village_name", city.name)
            editor.putString("village_hindi_name", city.hindiName)
            editor.putString("village_state", city.state)
            editor.putString("village_hindi_state", city.hindiState)
            editor.putString("village_district", city.district)
            editor.putString("village_hindi_district", city.hindiDistrict)
            editor.putLong("village_lat_bits", java.lang.Double.doubleToLongBits(city.latitude))
            editor.putLong("village_lon_bits", java.lang.Double.doubleToLongBits(city.longitude))
            editor.putString("city_id", city.id)
        } else {
            editor.putBoolean("is_custom_village", false)
            editor.putString("city_id", city.id)
        }
        editor.apply()
    }

    var ritualStyle: String
        get() = prefs.getString("ritual_style", "General") ?: "General"
        set(value) = prefs.edit().putString("ritual_style", value).apply()

    var isDarkMode: Boolean
        get() = prefs.getBoolean("is_dark_mode", false)
        set(value) = prefs.edit().putBoolean("is_dark_mode", value).apply()

    var remindersEnabled: Boolean
        get() = prefs.getBoolean("reminders_enabled", true)
        set(value) = prefs.edit().putBoolean("reminders_enabled", value).apply()

    var isPremium: Boolean
        get() = prefs.getBoolean("is_premium", false)
        set(value) = prefs.edit().putBoolean("is_premium", value).apply()

    // --- Personal festival companion ---
    fun plannedFestivalIds(): Set<String> = prefs.getStringSet("planned_festivals", emptySet()) ?: emptySet()

    fun isFestivalPlanned(festivalId: String): Boolean = plannedFestivalIds().contains(festivalId)

    fun setFestivalPlanned(festivalId: String, planned: Boolean) {
        val ids = plannedFestivalIds().toMutableSet()
        if (planned) ids.add(festivalId) else ids.remove(festivalId)
        prefs.edit().putStringSet("planned_festivals", ids).apply()
    }

    fun householdMembers(): List<String> =
        (prefs.getStringSet("household_members", emptySet()) ?: emptySet()).sorted()

    fun addHouseholdMember(name: String) {
        if (name.isBlank()) return
        prefs.edit().putStringSet("household_members", householdMembers().plus(name.trim()).toSet()).apply()
    }

    fun removeHouseholdMember(name: String) {
        prefs.edit().putStringSet("household_members", householdMembers().minus(name).toSet()).apply()
    }

    var selectedTradition: String
        get() = prefs.getString("selected_tradition", "General Indian") ?: "General Indian"
        set(value) = prefs.edit().putString("selected_tradition", value).apply()

    var templeNote: String
        get() = prefs.getString("temple_note", "") ?: ""
        set(value) = prefs.edit().putString("temple_note", value).apply()

    // --- App Update Preferences ---
    var lastUpdateCheckTime: Long
        get() = prefs.getLong("last_update_check_time", 0L)
        set(value) = prefs.edit().putLong("last_update_check_time", value).apply()

    var dismissedUpdateVersionCode: Int
        get() = prefs.getInt("dismissed_update_version_code", 0)
        set(value) = prefs.edit().putInt("dismissed_update_version_code", value).apply()

    var customUpdateEndpointUrl: String?
        get() = prefs.getString("custom_update_endpoint_url", null)
        set(value) = prefs.edit().putString("custom_update_endpoint_url", value).apply()

    // --- Interactive Puja Step Tracking ---
    fun isStepCompleted(festivalId: String, stepIndex: Int): Boolean {
        return prefs.getBoolean("step_${festivalId}_$stepIndex", false)
    }

    fun setStepCompleted(festivalId: String, stepIndex: Int, completed: Boolean) {
        prefs.edit().putBoolean("step_${festivalId}_$stepIndex", completed).apply()
    }

    fun toggleStep(festivalId: String, stepIndex: Int): Boolean {
        val newState = !isStepCompleted(festivalId, stepIndex)
        setStepCompleted(festivalId, stepIndex, newState)
        return newState
    }

    fun getCompletedStepsCount(festivalId: String, total: Int): Int {
        var count = 0
        for (i in 0 until total) {
            if (isStepCompleted(festivalId, i)) count++
        }
        return count
    }

    // --- Samagri / Shopping List Tracking ---
    fun isSupplyBought(festivalId: String, supplyIndex: Int): Boolean {
        return prefs.getBoolean("supply_${festivalId}_$supplyIndex", false)
    }

    fun setSupplyBought(festivalId: String, supplyIndex: Int, bought: Boolean) {
        prefs.edit().putBoolean("supply_${festivalId}_$supplyIndex", bought).apply()
    }

    fun toggleSupply(festivalId: String, supplyIndex: Int): Boolean {
        val newState = !isSupplyBought(festivalId, supplyIndex)
        setSupplyBought(festivalId, supplyIndex, newState)
        return newState
    }

    fun getBoughtSuppliesCount(festivalId: String, total: Int): Int {
        var count = 0
        for (i in 0 until total) {
            if (isSupplyBought(festivalId, i)) count++
        }
        return count
    }

    // --- Custom Shopping Items ---
    fun getCustomSupplies(festivalId: String): List<Pair<String, String>> {
        val raw = prefs.getStringSet("custom_supplies_$festivalId", emptySet()) ?: emptySet()
        return raw.mapNotNull {
            val parts = it.split("|||")
            if (parts.size == 2) parts[0] to parts[1] else null
        }
    }

    fun addCustomSupply(festivalId: String, name: String, quantity: String) {
        val current = (prefs.getStringSet("custom_supplies_$festivalId", emptySet()) ?: emptySet()).toMutableSet()
        current.add("$name|||$quantity")
        prefs.edit().putStringSet("custom_supplies_$festivalId", current).apply()
    }

    fun removeCustomSupply(festivalId: String, name: String, quantity: String) {
        val current = (prefs.getStringSet("custom_supplies_$festivalId", emptySet()) ?: emptySet()).toMutableSet()
        current.remove("$name|||$quantity")
        prefs.edit().putStringSet("custom_supplies_$festivalId", current).apply()
    }

    // --- Reminders per festival ---
    fun isReminderActive(festivalId: String, type: String): Boolean {
        return prefs.getBoolean("remind_${festivalId}_$type", true)
    }

    fun setReminderActive(festivalId: String, type: String, active: Boolean) {
        prefs.edit().putBoolean("remind_${festivalId}_$type", active).apply()
    }

    fun resetFestivalProgress(festivalId: String, totalSteps: Int, totalSupplies: Int) {
        val editor = prefs.edit()
        for (i in 0 until totalSteps) {
            editor.remove("step_${festivalId}_$i")
        }
        for (i in 0 until totalSupplies) {
            editor.remove("supply_${festivalId}_$i")
        }
        editor.apply()
    }
}
