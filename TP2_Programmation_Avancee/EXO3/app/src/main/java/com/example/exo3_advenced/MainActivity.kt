package com.example.exo3_advenced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val CUSTOM_INTENT = "com.example.exo3_advenced"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        btnSend.setOnClickListener {

            println("flutter")
            val i = Intent()
            i.action = CUSTOM_INTENT
            i.setClass(this, TestReceiver::class.java)
            context.sendBroadcast(i)
        }
        btnStartService.setOnClickListener { DemoService.demarrerService(context) }
    }
}
