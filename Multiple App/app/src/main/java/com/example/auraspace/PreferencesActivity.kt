package com.example.auraspace

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val vibeInput = findViewById<EditText>(R.id.vibeInput)
        val notificationCheck = findViewById<CheckBox>(R.id.notificationCheck)
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        val backHomeBtn = findViewById<Button>(R.id.backHomeBtn)
        val savedText = findViewById<TextView>(R.id.savedText)

        val sharedPref = getSharedPreferences("AuraPrefs", Context.MODE_PRIVATE)

        val savedVibe = sharedPref.getString("vibe", "")
        val savedNotifications = sharedPref.getBoolean("notifications", false)

        vibeInput.setText(savedVibe)
        notificationCheck.isChecked = savedNotifications

        savedText.text = if (savedVibe != "") {
            "Saved vibe: $savedVibe\nNotifications: ${if (savedNotifications) "On" else "Off"}"
        } else {
            "No preferences saved yet."
        }

        saveBtn.setOnClickListener {
            val vibe = vibeInput.text.toString()

            sharedPref.edit()
                .putString("vibe", vibe)
                .putBoolean("notifications", notificationCheck.isChecked)
                .apply()

            savedText.text =
                "Saved vibe: $vibe\nNotifications: ${if (notificationCheck.isChecked) "On" else "Off"}"

            Toast.makeText(this, "Preferences saved", Toast.LENGTH_SHORT).show()
        }

        backHomeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}