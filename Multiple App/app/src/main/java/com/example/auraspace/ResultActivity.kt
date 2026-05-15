package com.example.auraspace

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val mood = intent.getStringExtra("mood") ?: "Calm"

        val sharedPref = getSharedPreferences("AuraPrefs", Context.MODE_PRIVATE)
        val savedVibe = sharedPref.getString("vibe", "your saved vibe")
        val notifications = sharedPref.getBoolean("notifications", false)

        val moodText = findViewById<TextView>(R.id.moodText)
        val emojiText = findViewById<TextView>(R.id.emojiText)
        val planText = findViewById<TextView>(R.id.planText)
        val tipText = findViewById<TextView>(R.id.tipText)
        val infoBtn = findViewById<Button>(R.id.infoBtn)
        val backHomeBtn = findViewById<Button>(R.id.backHomeBtn)

        moodText.text = "Selected mood: $mood"

        when (mood) {
            "Calm" -> {
                emojiText.text = "🌙"
                planText.text = "Calm Focus Plan"
                tipText.text = "Use your saved vibe: $savedVibe.\nTry soft lighting, calm music, and a 25-minute focused study session."
            }

            "Motivated" -> {
                emojiText.text = "⚡"
                planText.text = "High Energy Plan"
                tipText.text = "Use your saved vibe: $savedVibe.\nStart with your hardest task first and finish with a small reward."
            }

            "Tired" -> {
                emojiText.text = "☁️"
                planText.text = "Gentle Reset Plan"
                tipText.text = "Use your saved vibe: $savedVibe.\nChoose one small task, drink water, and take a short break."
            }

            "Stressed" -> {
                emojiText.text = "🌧"
                planText.text = "Stress Relief Plan"
                tipText.text = "Use your saved vibe: $savedVibe.\nBreathe slowly, clear your desk, and begin with one simple task."
            }
        }

        if (notifications) {
            tipText.append("\n\nReminder: Notifications are enabled for your productivity routine.")
        }

        infoBtn.setOnClickListener {
            showInfoPopup()
        }

        backHomeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showInfoPopup() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_info)

        val closeBtn = dialog.findViewById<Button>(R.id.closeBtn)

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}