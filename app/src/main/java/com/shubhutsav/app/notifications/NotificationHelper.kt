package com.shubhutsav.app.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.shubhutsav.app.MainActivity

object NotificationHelper {

    const val CHANNEL_ID = "shubh_utsav_reminders"
    private const val CHANNEL_NAME = "Festival & Puja Reminders"
    private const val CHANNEL_DESC = "Notifications for festival preparation, shopping checklists, and auspicious muhurat timings."

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESC
                enableLights(true)
                enableVibration(true)
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun sendNotification(
        context: Context,
        notificationId: Int,
        title: String,
        message: String,
        festivalId: String? = null
    ) {
        createNotificationChannel(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            festivalId?.let { putExtra("festival_id", it) }
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            val manager = NotificationManagerCompat.from(context)
            manager.notify(notificationId, builder.build())
        } catch (e: SecurityException) {
            // Permission not granted on Android 13+
        }
    }

    fun sendTestReminder(context: Context, isHindi: Boolean = false, language: String = if (isHindi) "hi" else "en") {
        val title = when (language) {
            "mr" -> "🪔 शुभ उत्सव: स्मरणपत्र"
            "hi" -> "🪔 शुभ उत्सव: स्मरण पत्र"
            else -> "🪔 Shubh Utsav: Reminder"
        }
        val message = when (language) {
            "mr" -> "दिवाळी लक्ष्मीपूजन मुहूर्त उद्या संध्याकाळी ०५:४० वाजता सुरू होईल. आपली साहित्य यादी तपासा."
            "hi" -> "दीपावली पूजन मुहूर्त कल शाम 05:40 बजे से है। अपनी पूजा सामग्री सूची जांच लें।"
            else -> "Diwali Lakshmi Puja Muhurat begins tomorrow at 05:40 PM. Check your shopping checklist!"
        }

        sendNotification(context, 1001, title, message, "diwali")
    }
}
