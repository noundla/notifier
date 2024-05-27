package com.nsdlabs.notifier

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.textfield.TextInputEditText


class SetupActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var button : View
    private lateinit var keysInput : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)
        button = findViewById<View>(R.id.mybutton);
        keysInput = findViewById<TextInputEditText>(R.id.keys_input);
        toolbar = findViewById<Toolbar>(R.id.toolbar);


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // Enable notification access
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        })

        // Pre-fill the selected text
        keysInput.setText(Util.getKey(applicationContext))
        keysInput.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Util.saveKey(applicationContext, keysInput.text.toString())
            }
            return@OnEditorActionListener false
        })
    }
}