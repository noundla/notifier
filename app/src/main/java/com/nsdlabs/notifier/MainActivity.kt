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
    private lateinit var keyTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<View>(R.id.setupButton);
        sound = findViewById<View>(R.id.sound);
        keyTextView = findViewById<TextView>(R.id.keyText)



        // Enable notification accesssuper.onStart()
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SetupActivity::class.java)
            startActivity(intent)
        })

        //stop sound button
        sound.setOnClickListener(View.OnClickListener {
            Util.stopSound(applicationContext)
        })
    }

    override fun onStart() {
        super.onStart()
        keyTextView.setText("Listening for text: ${Util.getKey(applicationContext)}")
    }
}