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
