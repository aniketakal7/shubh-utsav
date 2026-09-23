package com.shubhutsav.app.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title
            ?: remoteMessage.data["title"]
            ?: "🪔 Shubh Utsav"

        val body = remoteMessage.notification?.body
            ?: remoteMessage.data["body"]
            ?: remoteMessage.data["message"]
            ?: "New festival update & auspicious muhurat."

        val festivalId = remoteMessage.data["festival_id"]

        val notificationId = (System.currentTimeMillis() % 100000).toInt()
        NotificationHelper.sendNotification(
            context = applicationContext,
            notificationId = notificationId,
            title = title,
            message = body,
            festivalId = festivalId
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "New Firebase FCM Registration Token: $token")
    }

    companion object {
        private const val TAG = "FCMService"
    }
}
