package com.example.exo1_persistance

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private val sharedPrefFile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPrefs = getSharedPreferences(sharedPrefFile, 0)
        val editeur = sharedPrefs.edit()
        editeur.putString("color", Color.RED.toString() )
        editeur.commit()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener{

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }


        button3.setOnClickListener{
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }




        val color = sharedPrefs.getString("color", Color.BLUE.toString())

        if (color != null) {
            mainLa.setBackgroundColor(color.toInt())
        }



    }

    override fun onResume() {
        super.onResume()
        val sharedPrefs = getSharedPreferences(sharedPrefFile, 0)
        val color = sharedPrefs.getString("color", Color.BLUE.toString())

        if (color != null) {
            mainLa.setBackgroundColor(color.toInt())
        }

    }
}
