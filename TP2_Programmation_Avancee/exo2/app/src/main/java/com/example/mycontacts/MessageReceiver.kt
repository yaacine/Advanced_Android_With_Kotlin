package com.example.mycontacts


import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService



class MessageReceiver : BroadcastReceiver() {

    private val channelId = "channel-01"
    private val channelName = "SIL Channel"
    @RequiresApi(Build.VERSION_CODES.N)
    private val importance = NotificationManager.IMPORTANCE_HIGH

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("notif","invoqued")
        var titre = ""
        var contenu = ""
        if (intent.action === "dz.esi.demobroadcast") {
            titre = "Un titre"
            contenu = "Diffusion"
        } else {
            titre = "Un appel"
            contenu = "Appel suivi"
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
            val noti = Notification.Builder(context,"sil")
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(false)

                .build()
            notificationManager.notify((1..12).shuffled().first(), noti)
            Log.d("notif","sent1")

        }else{

            val noti = Notification.Builder(context)
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star).setAutoCancel(false)

                .build()
            notificationManager.notify(  (1..102).shuffled().first(), noti)
            Log.d("notif","sent2")


        }

    }
}

public fun setNotificationAlarm(context: Context) {
    val intent = Intent()
    intent.setClass(context, MessageReceiver::class.java)



    val pendingIntent =
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)


    Log.d("ME", "Alarm started")
}