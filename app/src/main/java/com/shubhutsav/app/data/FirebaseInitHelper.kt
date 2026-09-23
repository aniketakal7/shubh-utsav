package com.shubhutsav.app.data

import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging

object FirebaseInitHelper {

    private const val TAG = "FirebaseInitHelper"

    fun init(context: Context) {
        try {
            // Ensure Firebase is initialized
            FirebaseApp.initializeApp(context)

            // Enable Crashlytics & Analytics collection
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            val analytics = FirebaseAnalytics.getInstance(context)
            analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)

            // Subscribe to default broadcast topics for festival alerts & daily panchang
            FirebaseMessaging.getInstance().subscribeToTopic("all_users")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Subscribed to 'all_users' topic")
                    }
                }

            FirebaseMessaging.getInstance().subscribeToTopic("festivals")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Subscribed to 'festivals' topic")
                    }
                }

            Log.i(TAG, "Firebase initialized successfully with Analytics, Crashlytics, and Messaging")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Firebase: ${e.message}", e)
        }
    }
}
