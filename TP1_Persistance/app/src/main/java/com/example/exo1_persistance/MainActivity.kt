package com.example.exo1_persistance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        println("color is :===>")

        val sharedPrefs = getSharedPreferences(sharedPrefFile, 0)

/*

        val editeur = sharedPrefs.edit()
        editeur.putString("color", "#D81B60" )
        editeur.commit()

*/

        val color = sharedPrefs.getString("color", "défaut")
        println("color is :===>" +color)



    }
}
