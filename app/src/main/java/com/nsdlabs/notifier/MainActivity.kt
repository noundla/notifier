package com.nsdlabs.notifier

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    private lateinit var button : View
    private lateinit var sound : View
    private lateinit var text : View
    private lateinit var keysInput : TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<View>(R.id.mybutton);
        sound = findViewById<View>(R.id.sound);
        text = findViewById<View>(R.id.text);
        keysInput = findViewById<TextInputEditText>(R.id.keys_input);
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        })
        sound.setOnClickListener(View.OnClickListener {
            Util.stopSound(applicationContext)
        })
        keysInput.setText(Util.getKey(applicationContext))
        keysInput.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Util.saveKey(applicationContext, keysInput.text.toString())
            }
            return@OnEditorActionListener false
        })
    }

    override fun onResume() {
        super.onResume()
//        if (checkNotificationPermission()) {
//            button.visibility = View.GONE
//            text.visibility = View.VISIBLE
//        } else {
//            button.visibility = View.VISIBLE
//            text.visibility = View.GONE
//        }

    }

    private fun checkNotificationPermission() : Boolean {
        val manager = NotificationManagerCompat.from(this)
        return manager.areNotificationsEnabled()
    }

}