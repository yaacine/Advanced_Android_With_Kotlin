package com.example.exo1_persistance

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate

class OptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)


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
                    val current = LocalDate.now()

                    when(position){
                        0 -> {

                        }

                        1 ->{

                        }

                        2-> {

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
        }
    }
}
