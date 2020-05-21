package com.example.exo3_advenced

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val CUSTOM_INTENT = "com.example.exo3_advenced"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val context = this




        btnSend.setOnClickListener {

            val intent = Intent(this , AdanService::class.java)
            startService(intent)


        }


        btnStartService.setOnClickListener { DemoService.demarrerService(context) }
    }
}
