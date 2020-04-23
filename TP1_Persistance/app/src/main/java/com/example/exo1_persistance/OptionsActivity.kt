package com.example.exo1_persistance

import android.app.Application
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_options.*
import java.time.LocalDate

class OptionsActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)


        val sharedPrefs = getSharedPreferences(sharedPrefFile, 0)

        val editeur = sharedPrefs.edit()

        var color = sharedPrefs.getString("color", Color.BLUE.toString())
        if (color != null) {
            optionLa.setBackgroundColor(color.toInt())
        }



        val colors = resources.getStringArray(R.array.displays)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val SpinAdapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, colors)
            spinner.adapter = SpinAdapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    print("posiotionnn====>"+position)



                    when(position){
                        0 -> {
                            editeur.putString("color", Color.RED.toString() )
                            editeur.commit()
                            println("color ===> RED")
                        }

                        1 ->{
                            editeur.putString("color",  Color.GREEN.toString() )
                            editeur.commit()
                            println("color ===> GRENN")

                        }

                        2-> {
                            editeur.putString("color",  Color.BLUE.toString()  )
                            editeur.commit()
                            println("color ===> BLUE")

                        }
                    }

                    Toast.makeText(this@OptionsActivity,
                        "color you choosed" + " " +
                                "" + colors[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }



            applayBtn.setOnClickListener{
                color = sharedPrefs.getString("color", Color.BLUE.toString())
                if (color != null) {
                    optionLa.setBackgroundColor(color!!.toInt())
                }
            }
        }
    }
}
