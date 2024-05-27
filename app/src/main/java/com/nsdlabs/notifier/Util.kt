package com.nsdlabs.notifier

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast

object Util {
    private const val SHARED_PREF_NAME : String = "notifierPref"
    private const val EXTRA_SP_SEARCH_KEY : String = "searchKey"
    private const val EXTRA_SP_LAST_NOTIFICATION_POSTED_TIME = "lastNotificationPostedTime"
    var ringTone : Ringtone? = null

    public fun saveKey(context: Context, searchKey: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        with(sharedPreferences.edit()) {
            putString(EXTRA_SP_SEARCH_KEY, searchKey)
            apply()
            Toast.makeText(context, "Listens for text \"$searchKey\"",Toast.LENGTH_SHORT).show()
        }
    }

    public fun getKey(context: Context) : String {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EXTRA_SP_SEARCH_KEY, "") ?: "";
    }

    public fun saveNotificationPostedTime(context: Context, millis:Long) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        with(sharedPreferences.edit()) {
            putLong(com.nsdlabs.notifier.Util.EXTRA_SP_LAST_NOTIFICATION_POSTED_TIME, millis)
            apply()
        }
    }

    public fun getLastNotificationPostedTime(context: Context) : Long {
        val sharedPreferences = context.getSharedPreferences(EXTRA_SP_LAST_NOTIFICATION_POSTED_TIME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(EXTRA_SP_SEARCH_KEY, -1) ?: -1;
    }

    public fun playSound(context: Context) {
        if (ringTone?.isPlaying == true) {
            Toast.makeText(context,"Stopping the already playing sound",Toast.LENGTH_SHORT).show()
            ringTone!!.stop()
        }
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringTone = RingtoneManager.getRingtone(context, notification)
        ringTone?.play()
    }

    public fun stopSound(context:Context) {
        if (ringTone == null || ringTone?.isPlaying == false) {
            Toast.makeText(context,"No Sound is playing",Toast.LENGTH_SHORT).show()
        } else {
            ringTone?.stop()
            Toast.makeText(context, "Stopping sound", Toast.LENGTH_LONG).show()
        }
    }
}