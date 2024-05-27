package com.nsdlabs.notifier

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri

object Util {
    val sharedPrefName : String = "notifierPref"
    val searchKeyPrefName : String = "searchKey"
    var ringTone : Ringtone? = null

    public fun saveKey(context: Context, searchKey: String) {
        val sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        with(sharedPreferences.edit()) {
            putString(searchKeyPrefName, searchKey)
            apply()
        }
    }

    public fun getKey(context: Context) : String {
        val sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(searchKeyPrefName, "") ?: "";
    }

    public fun playSound(context: Context) {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringTone = RingtoneManager.getRingtone(context, notification)
        ringTone?.play()
    }

    public fun stopSound(context:Context) {
        ringTone?.stop();
    }
}