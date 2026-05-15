package com.example.auraspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startBtn)
        val preferencesBtn = findViewById<Button>(R.id.preferencesBtn)

        startBtn.setOnClickListener {
            startActivity(Intent(this, MoodActivity::class.java))
        }

        preferencesBtn.setOnClickListener {
            startActivity(Intent(this, PreferencesActivity::class.java))
        }
    }
}