package com.example.exo3_advenced

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import kotlinx.android.synthetic.main.activity_main.*

class AdanReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
       // TODO("AdanReceiver.onReceive() is not implemented")


        val pIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), intent, 0)

        println("adan reciever here")
        var sound: Uri =
            Uri.parse("android.resource://" + "com.example.exo3_advenced" + "/" + R.raw.azan1)

        var mediaPlayer = MediaPlayer.create(context, sound)
        mediaPlayer?.setOnPreparedListener({
            println("ready")
        })

    }
}
