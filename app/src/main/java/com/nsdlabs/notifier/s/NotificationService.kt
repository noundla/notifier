package com.nsdlabs.notifier.s

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.nsdlabs.notifier.Util


class NotificationService : NotificationListenerService() {
    private val TAG = "NotificationService"
    private lateinit var context : Context

    override fun onCreate() {
        context = applicationContext
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn != null) {
            Log.d(TAG, "onNotificationPosted")
            Log.d(TAG, "ID: ${sbn.id}, text: ${sbn.notification.tickerText}, packageName: ${sbn.packageName}")
            val extras = sbn.notification.extras;

            val text = extras.getString("android.text", "")
            val title = extras.getString("android.title", "")
            Log.d(TAG, "text: $text , title: $title")
            val expText = Util.getKey(context)
            if (text != null && text.contains(expText, true) || title != null && title.contains(expText, true)) {
                Util.playSound(context)
                Log.d(TAG, "Starting ring")
            }
        }

    }
}