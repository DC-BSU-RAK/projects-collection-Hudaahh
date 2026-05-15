package com.example.fitfinder

import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var outfitResult: TextView
    private lateinit var resultCard: LinearLayout

    private var finalOutfit = ""
    private var finalDetails = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherGroup = findViewById<RadioGroup>(R.id.weatherGroup)
        val occasionGroup = findViewById<RadioGroup>(R.id.occasionGroup)
        val moodGroup = findViewById<RadioGroup>(R.id.moodGroup)

        val calculateBtn = findViewById<Button>(R.id.calculateBtn)
        val detailsBtn = findViewById<Button>(R.id.detailsBtn)

        outfitResult = findViewById(R.id.outfitResult)
        resultCard = findViewById(R.id.resultCard)

        calculateBtn.setOnClickListener {
            val weather = getSelectedText(weatherGroup)
            val occasion = getSelectedText(occasionGroup)
            val mood = getSelectedText(moodGroup)

            if (weather == "" || occasion == "" || mood == "") {
                Toast.makeText(this, "Please select one option from each section", Toast.LENGTH_SHORT).show()
            } else {
                calculateOutfit(weather, occasion, mood)
                outfitResult.text = finalOutfit
                resultCard.visibility = LinearLayout.VISIBLE
            }
        }

        detailsBtn.setOnClickListener {
            showDetailsModal()
        }
    }

    private fun getSelectedText(group: RadioGroup): String {
        val selectedId = group.checkedRadioButtonId
        if (selectedId == -1) return ""

        val selectedButton = findViewById<RadioButton>(selectedId)
        return selectedButton.text.toString()
    }

    private fun calculateOutfit(weather: String, occasion: String, mood: String) {
        finalOutfit = when {
            weather == "Hot" && occasion == "Formal" && mood == "Confident" ->
                "Light blazer, formal shirt, trousers, and loafers"

            weather == "Cold" && occasion == "Formal" ->
                "Long coat, smart jumper, trousers, and boots"

            weather == "Rainy" ->
                "Waterproof jacket, dark jeans, and comfortable boots"

            occasion == "Party" && mood == "Confident" ->
                "Stylish top, statement jacket, fitted trousers, and dress shoes"

            occasion == "Sports" ->
                "Breathable t-shirt, joggers, trainers, and a light hoodie"

            mood == "Lazy" ->
                "Oversized hoodie, relaxed jeans, and comfortable trainers"

            mood == "Moody" ->
                "Dark jacket, plain top, black jeans, and boots"

            else ->
                "Casual shirt, comfortable jeans, and clean sneakers"
        }

        finalDetails =
            "This outfit suits $weather weather, a $occasion occasion, and a $mood mood. " +
                    "Fit Finder works like a creative calculator by combining your choices to recommend an outfit instead of calculating numbers."
    }

    private fun showDetailsModal() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_outfit_details)

        val detailsText = dialog.findViewById<TextView>(R.id.detailsText)
        val closeBtn = dialog.findViewById<Button>(R.id.closeBtn)
        val tryAgainBtn = dialog.findViewById<Button>(R.id.tryAgainBtn)

        detailsText.text = finalDetails

        closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        tryAgainBtn.setOnClickListener {
            dialog.dismiss()
            resultCard.visibility = LinearLayout.GONE
        }

        dialog.show()
    }
}