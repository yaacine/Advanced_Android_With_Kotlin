package com.example.exo3_advenced

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import java.util.*

class AdanService : Service() {


    val CUSTOM_INTENT = "com.example.exo3_advenced"

    override fun onBind(intent: Intent): IBinder {

        TODO("Return the communication channel to the service.")
    }

    lateinit var sound: Uri
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        sound =
            Uri.parse("android.resource://" + "com.example.exo3_advenced" + "/" + R.raw.azan1)

        mediaPlayer = MediaPlayer.create(this, sound)
        mediaPlayer?.setOnPreparedListener({
            println("ready")
        })

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        /*

        val runnable = Runnable {

            while (true) {
                println("started while")


                if (true) {
                    try {
                        val notificationManager =
                            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        val notificationID = 1001

                        val pIntent =
                            PendingIntent.getService(
                                this,
                                System.currentTimeMillis().toInt(),
                                intent!!,
                                0
                            )


                        //getting the adan sound

                        val builder = builder(notificationManager, pIntent)
                        var noti = builder!!.build()
                        noti.sound = sound
                        noti.defaults = Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE
                        builder.setContentText("It's time for something prayer!")
                        noti = builder.build()


                        mediaPlayer.start()
                        notificationManager.notify(notificationID, noti)
                        //startForeground(1, noti)


                    } catch (ex: Exception) {

                    }
                }
                println("started service")
                Thread.sleep(6000)
                println("started service stop waiting")


            }

        }

        val thread = Thread(runnable)
        thread.start()
*/


        // reciever to handle adan sound
        println("flutter")
        val i = Intent()
        i.action = CUSTOM_INTENT
        i.setClass(this, AdanReceiver::class.java)
        var context = this
        context.sendBroadcast(i)


        // bringing adan data
        var adans = arrayListOf<Int>(
            AdanData.FajrSEC,
            AdanData.DohrSEC,
            AdanData.AsrSEC,
            AdanData.MaghribSEC,
            AdanData.IshaSEC
        )


        while (true) {
            println("started while")

            val currentTime = Calendar.getInstance()
            var currentTimeSEC =
                (currentTime.get(Calendar.HOUR) * 60 + currentTime.get(Calendar.MINUTE)) * 60
            println(currentTime)
            if (currentTimeSEC in adans) {


                try {
                    val notificationManager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationID = 1001

                    val pIntent =
                        PendingIntent.getService(
                            this,
                            System.currentTimeMillis().toInt(),
                            intent!!,
                            0
                        )


                    //getting the adan sound

                    val builder = builder(notificationManager, pIntent)
                    var noti = builder!!.build()
                    noti.sound = sound
                    noti.defaults = Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE
                    builder.setContentText("It's time for prayer!")
                    noti = builder.build()


                    mediaPlayer.start()
                    notificationManager.notify(notificationID, noti)
                    //startForeground(1, noti)


                } catch (ex: Exception) {

                }
            }
            println("started service")
            Thread.sleep(60000)
            println("started service stop waiting")


        }


        return START_STICKY
        //return super.onStartCommand(intent, flags, startId)
    }


    private fun builder(nm: NotificationManager, pi: PendingIntent): Notification.Builder? {
        var sound: Uri =
            Uri.parse("android.resource://" + "com.example.exo3_advenced" + "/" + R.raw.azan1)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                "ch00", "ch00", NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "Endless Service channel"
                it.enableLights(true)
                it.lightColor = Color.RED
                it.enableVibration(true)
                it.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                it
            }



            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()

            mChannel.setSound(sound, audioAttributes);
            nm.createNotificationChannel(mChannel)

            val builder = Notification.Builder(this, "ch00")
                .setContentTitle("Adan time")
                .setContentText("Notification à partir d'un service")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pi).setAutoCancel(true)
                //.setOngoing(true)
                .setAutoCancel(false)
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
                .setAutoCancel(false)
                .setSound(sound)

            return builder

        }

    }


    /*
    private fun startService() {
        if (isServiceStarted) return
        log("Starting the foreground service task")
        Toast.makeText(this, "Service starting its task", Toast.LENGTH_SHORT).show()
        isServiceStarted = true
        setServiceState(this, ServiceState.STARTED)

        // we need this lock so our service gets not affected by Doze Mode
        wakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "EndlessService::lock").apply {
                    acquire()
                }
            }

        // we're starting a loop in a coroutine
        GlobalScope.launch(Dispatchers.IO) {
            while (isServiceStarted) {
                launch(Dispatchers.IO) {
                    pingFakeServer()
                }
                delay(1 * 60 * 1000)
            }
            log("End of the loop for the service")
        }
    }

     */

}
