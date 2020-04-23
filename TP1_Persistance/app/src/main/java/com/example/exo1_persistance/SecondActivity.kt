package com.example.exo1_persistance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val sharedPrefs = getSharedPreferences(sharedPrefFile, 0)
        val color = sharedPrefs.getString("color", Color.BLUE.toString())

        if (color != null) {
            secondLa.setBackgroundColor(color.toInt())
        }
    }
}
