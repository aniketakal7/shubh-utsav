package com.shubhutsav.app.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shubhutsav.app.data.FestivalRepository
import com.shubhutsav.app.data.PreferencesManager
import com.shubhutsav.app.data.getName

/**
 * BroadcastReceiver that fires when a scheduled festival reminder alarm triggers.
 * Also handles BOOT_COMPLETED to re-schedule alarms after device restart.
 */
class FestivalReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED -> {
                // Re-schedule all active reminders after device reboot
                rescheduleAllReminders(context)
            }
            else -> {
                // Alarm triggered: deliver the notification
                val festivalId = intent.getStringExtra("alarm_festival_id") ?: return
                val reminderType = intent.getStringExtra("alarm_reminder_type") ?: return
                val festivalName = intent.getStringExtra("alarm_festival_name") ?: "Festival"
                val muhuratTime = intent.getStringExtra("alarm_muhurat_time") ?: ""
                val language = intent.getStringExtra("alarm_language") ?: "en"

                val (title, message) = buildNotificationContent(
                    festivalName, reminderType, muhuratTime, language
                )

                NotificationHelper.sendNotification(
                    context = context,
                    notificationId = "${festivalId}_$reminderType".hashCode(),
                    title = title,
                    message = message,
                    festivalId = festivalId
                )
            }
        }
    }

    private fun buildNotificationContent(
        festivalName: String,
        reminderType: String,
        muhuratTime: String,
        language: String
    ): Pair<String, String> {
        return when (reminderType) {
            "prep" -> when (language) {
                "mr" -> Pair(
                    "🪔 $festivalName — तयारी स्मरण",
                    "उद्या $festivalName आहे! घराची स्वच्छता, पूजा चौरंग सजवणे व मूर्ती तपासणे आवश्यक आहे."
                )
                "hi" -> Pair(
                    "🪔 $festivalName — तैयारी स्मरण",
                    "कल $festivalName है! घर की सफाई, पूजा चौकी सजाना और मूर्ती की जांच करें।"
                )
                else -> Pair(
                    "🪔 $festivalName — Preparation Reminder",
                    "$festivalName is tomorrow! Clean your puja chowki, check idols, and prepare the puja space."
                )
            }
            "shopping" -> when (language) {
                "mr" -> Pair(
                    "🛒 $festivalName — साहित्य खरेदी स्मरण",
                    "$festivalName २ दिवसांत आहे! ताजी फुले, दीप, तूप, कापूर, प्रसाद व फळे आणा."
                )
                "hi" -> Pair(
                    "🛒 $festivalName — सामग्री खरीदारी स्मरण",
                    "$festivalName 2 दिन बाद है! ताज़े फूल, दीये, घी, कपूर, प्रसाद और फल लाएं।"
                )
                else -> Pair(
                    "🛒 $festivalName — Shopping Reminder",
                    "$festivalName is in 2 days! Get fresh flowers, diyas, ghee, camphor, fruits and prasad."
                )
            }
            "muhurat" -> when (language) {
                "mr" -> Pair(
                    "🪔 $festivalName — मुहूर्त स्मरण",
                    "शुभ मुहूर्त सुरू होण्यास ३० मिनिटे बाकी ($muhuratTime). दीप प्रज्वलित करा."
                )
                "hi" -> Pair(
                    "🪔 $festivalName — मुहूर्त स्मरण",
                    "शुभ मुहूर्त शुरू होने में 30 मिनट शेष ($muhuratTime)। दीप प्रज्ज्वलित करें।"
                )
                else -> Pair(
                    "🪔 $festivalName — Muhurat Alert",
                    "Auspicious muhurat starts in 30 minutes ($muhuratTime). Prepare your diyas and begin puja."
                )
            }
            else -> Pair("🪔 $festivalName", "Festival reminder")
        }
    }

    /**
     * Re-schedules all active reminders from persisted preferences.
     * Called after BOOT_COMPLETED or app restart.
     */
    companion object {
        fun rescheduleAllReminders(context: Context) {
            try {
                val prefs = PreferencesManager(context)
                val language = prefs.languageCode

                FestivalRepository.festivals.forEach { festival ->
                    val types = listOf("prep", "shopping", "muhurat")
                    types.forEach { type ->
                        if (prefs.isReminderActive(festival.id, type)) {
                            AlarmScheduler.scheduleReminder(
                                context = context,
                                festivalId = festival.id,
                                reminderType = type,
                                festivalDateEn = festival.date2026En,
                                muhuratTimeEn = festival.muhurat.timeEn,
                                festivalName = festival.getName(language),
                                language = language
                            )
                        }
                    }
                }
            } catch (_: Exception) {
                // Silently fail — don't crash on boot
            }
        }
    }
}
