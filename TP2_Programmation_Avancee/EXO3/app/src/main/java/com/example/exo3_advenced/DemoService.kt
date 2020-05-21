package com.example.exo3_advenced

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri


class DemoService : IntentService("DemoService") {


    override fun onHandleIntent(intent: Intent?) {
        try {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationID = 1001
            val pIntent =
                PendingIntent.getService(this, System.currentTimeMillis().toInt(), intent!!, 0)

            val builder = builder(notificationManager, pIntent)
            var noti = builder!!.build()

            var sound: Uri =
                Uri.parse("android.resource://" + "com.example.exo3_advenced" + "/" + R.raw.azan1)


            //  var mediaPlayer = MediaPlayer.create(this, R.raw.azan1)
            var mediaPlayer = MediaPlayer.create(this, sound)
            mediaPlayer?.setOnPreparedListener({
                println("ready")
            })


            while (true) {

            }
            //    var  sound : Uri = Uri.parse("android.resource://" + "com.example.exo3_advenced" + "/"+ R.raw.azan1)

            // noti.sound= sound
            // noti.defaults = Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE

            //Thread.sleep(6000)
            mediaPlayer.start()

            //noti.sound = sound
            notificationManager.notify(notificationID, noti)

            Thread.sleep(6000)
            builder.setProgress(100, 33, false)
            noti = builder.build()
            notificationManager.notify(notificationID, noti)
            Thread.sleep(6000)
            builder.setProgress(100, 66, false)
            noti = builder.build()
            notificationManager.notify(notificationID, noti)
            Thread.sleep(6000)
            builder.setProgress(100, 100, false).setContentText("Terminé !")
            noti = builder.build()
            notificationManager.notify(notificationID, noti)


        } catch (ex: Exception) {

        }

    }


    private fun builder(nm: NotificationManager, pi: PendingIntent): Notification.Builder? {
        var sound: Uri =
            Uri.parse("android.resource://" + "com.example.exo3_advenced" + "/" + R.raw.azan1)


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                "ch00", "ch00", NotificationManager.IMPORTANCE_HIGH
            )

            println("flutter")
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()

            mChannel.setSound(sound, audioAttributes);
            nm.createNotificationChannel(mChannel)

            val builder = Notification.Builder(this, "ch00")
                .setContentTitle("Service")
                .setContentText("Notification à partir d'un service")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pi).setAutoCancel(true)
                .setOngoing(true)
                .setProgress(100, 0, false)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSound(sound)
            return builder


        } else {

            val builder = Notification.Builder(this)
                .setContentTitle("Service")
                .setContentText("Notification à partir d'un service")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pi).setAutoCancel(true)
                .setOngoing(true)
                .setProgress(100, 0, false)
                .setAutoCancel(true)
                .setSound(sound)

            return builder

        }

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {
        // TODO: Handle action Foo
        throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String) {
        // TODO: Handle action Baz
        throw UnsupportedOperationException("Not yet implemented")
    }

    companion object {
        private val ACTION_SERVICE = "com.example.exo3_advenced"

        fun demarrerService(context: Context) {
            val intent = Intent(context, DemoService::class.java)
            intent.action = ACTION_SERVICE
            intent.type = Service.START_STICKY.toString()
            context.startService(intent)
        }
    }
}
