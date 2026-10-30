package com.shubhutsav.app.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Schedules and cancels exact alarms for festival reminders using AlarmManager.
 * Supports three reminder types per festival:
 *   - "prep"     → 1 day before the festival at 9:00 AM
 *   - "shopping" → 2 days before the festival at 10:00 AM
 *   - "muhurat"  → On festival day, 30 minutes before the muhurat start time
 */
object AlarmScheduler {

    private const val EXTRA_FESTIVAL_ID = "alarm_festival_id"
    private const val EXTRA_REMINDER_TYPE = "alarm_reminder_type"
    private const val EXTRA_FESTIVAL_NAME = "alarm_festival_name"
    private const val EXTRA_MUHURAT_TIME = "alarm_muhurat_time"
    private const val EXTRA_LANGUAGE = "alarm_language"

    /**
     * Generates a unique, stable request code per (festival, reminderType) pair
     * for PendingIntent identification.
     */
    private fun requestCode(festivalId: String, reminderType: String): Int {
        return ("${festivalId}_$reminderType").hashCode() and 0x7FFFFFFF
    }

    /**
     * Parses a date string like "12 Nov 2026" or "20 Oct 2025" into a Calendar.
     * Returns null if the date cannot be parsed.
     */
    private fun parseFestivalDate(dateEn: String): Calendar? {
        return try {
            val fmt = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
            val date = fmt.parse(dateEn) ?: return null
            Calendar.getInstance().apply { time = date }
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Parses a muhurat time string like "05:40 PM – 07:35 PM" and returns
     * the start hour and minute as a Pair(hour24, minute).
     */
    private fun parseMuhuratStartTime(muhuratTimeEn: String): Pair<Int, Int>? {
        return try {
            // Extract the first time (start time) e.g. "05:40 PM"
            val startPart = muhuratTimeEn.split("–", "—", "-").firstOrNull()?.trim() ?: return null
            val fmt = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
            val parsed = fmt.parse(startPart) ?: return null
            val cal = Calendar.getInstance().apply { time = parsed }
            Pair(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Schedule a reminder alarm for a specific festival and type.
     *
     * @param festivalDateEn The English date string e.g. "12 Nov 2026"
     * @param muhuratTimeEn  The muhurat time string e.g. "05:40 PM – 07:35 PM" (needed for muhurat type)
     * @param festivalName   Display name of the festival for notification content
     * @param language       User's current language code ("en", "hi", "mr")
     */
    fun scheduleReminder(
        context: Context,
        festivalId: String,
        reminderType: String,
        festivalDateEn: String,
        muhuratTimeEn: String,
        festivalName: String,
        language: String
    ) {
        val festivalCal = parseFestivalDate(festivalDateEn) ?: return

        val alarmCal = Calendar.getInstance()

        when (reminderType) {
            "prep" -> {
                // 1 day before at 9:00 AM
                alarmCal.timeInMillis = festivalCal.timeInMillis
                alarmCal.add(Calendar.DAY_OF_YEAR, -1)
                alarmCal.set(Calendar.HOUR_OF_DAY, 9)
                alarmCal.set(Calendar.MINUTE, 0)
                alarmCal.set(Calendar.SECOND, 0)
            }
            "shopping" -> {
                // 2 days before at 10:00 AM
                alarmCal.timeInMillis = festivalCal.timeInMillis
                alarmCal.add(Calendar.DAY_OF_YEAR, -2)
                alarmCal.set(Calendar.HOUR_OF_DAY, 10)
                alarmCal.set(Calendar.MINUTE, 0)
                alarmCal.set(Calendar.SECOND, 0)
            }
            "muhurat" -> {
                // On festival day, 30 minutes before muhurat start
                val startTime = parseMuhuratStartTime(muhuratTimeEn) ?: return
                alarmCal.timeInMillis = festivalCal.timeInMillis
                alarmCal.set(Calendar.HOUR_OF_DAY, startTime.first)
                alarmCal.set(Calendar.MINUTE, startTime.second)
                alarmCal.set(Calendar.SECOND, 0)
                alarmCal.add(Calendar.MINUTE, -30)
            }
            else -> return
        }

        // Don't schedule alarms in the past
        if (alarmCal.timeInMillis <= System.currentTimeMillis()) return

        val intent = Intent(context, FestivalReminderReceiver::class.java).apply {
            putExtra(EXTRA_FESTIVAL_ID, festivalId)
            putExtra(EXTRA_REMINDER_TYPE, reminderType)
            putExtra(EXTRA_FESTIVAL_NAME, festivalName)
            putExtra(EXTRA_MUHURAT_TIME, muhuratTimeEn)
            putExtra(EXTRA_LANGUAGE, language)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode(festivalId, reminderType),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        alarmCal.timeInMillis,
                        pendingIntent
                    )
                } else {
                    // Fallback to inexact alarm if exact alarm permission not granted
                    alarmManager.setAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        alarmCal.timeInMillis,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    alarmCal.timeInMillis,
                    pendingIntent
                )
            }
        } catch (_: SecurityException) {
            // Graceful fallback: use inexact alarm
            try {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    alarmCal.timeInMillis,
                    pendingIntent
                )
            } catch (_: Exception) {}
        }
    }

    /**
     * Cancel a previously scheduled reminder alarm.
     */
    fun cancelReminder(
        context: Context,
        festivalId: String,
        reminderType: String
    ) {
        val intent = Intent(context, FestivalReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode(festivalId, reminderType),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        alarmManager?.cancel(pendingIntent)
    }
}
