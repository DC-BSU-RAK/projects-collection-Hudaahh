package com.example.auraspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)

        findViewById<Button>(R.id.calmBtn).setOnClickListener {
            openResult("Calm")
        }

        findViewById<Button>(R.id.motivatedBtn).setOnClickListener {
            openResult("Motivated")
        }

        findViewById<Button>(R.id.tiredBtn).setOnClickListener {
            openResult("Tired")
        }

        findViewById<Button>(R.id.stressedBtn).setOnClickListener {
            openResult("Stressed")
        }
    }

    private fun openResult(mood: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("mood", mood)
        startActivity(intent)
    }
}