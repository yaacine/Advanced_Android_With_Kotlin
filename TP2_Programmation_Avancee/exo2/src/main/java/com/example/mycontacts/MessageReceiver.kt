package com.example.mycontacts


import android.annotation.SuppressLint
import android.provider.Telephony.Sms
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import androidx.core.content.ContextCompat
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.example.mycontacts.database.AppDatabase
import com.example.mycontacts.database.ContactData
import com.example.mycontacts.database.ContactDataDAO


class MessageReceiver : BroadcastReceiver() {

    private val channelId = "channel-01"
    private val channelName = "SIL Channel"

    @RequiresApi(Build.VERSION_CODES.N)
    private val importance = NotificationManager.IMPORTANCE_HIGH

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("notif", "invoqued")


        if (intent.action == "test") {
            return
        }
        Log.d("intent______ URI", intent.toUri(0))


        val msgs = context.contentResolver.query(
            Sms.Inbox.CONTENT_URI,
            null,
            null,
            null,
            "date desc limit 1"
        )
        if (msgs != null) {
            while ((msgs.moveToNext())) {

                newMessageSendNotificationWithCheck(
                    msgs.getString(msgs.getColumnIndex(Sms.Inbox.ADDRESS)),
                    context
                )

            }
        }
        msgs?.close()


    }


    private fun newMessageSendNotificationWithCheck(str_number: String, active: Context) {
        var active_contacts: MutableList<ContactData> = ArrayList()
        var db: AppDatabase? = null
        var dao: ContactDataDAO? = null
        var found = false
        var person: ContactData? = null

        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {

                db = AppDatabase.getInstance(active)
                dao = db?.contactDao()

                active_contacts = dao?.getActiveContacts() as MutableList<ContactData>

                for (item in active_contacts) {
                    if (item.number == str_number) {
                        person = item
                        found  = true
                    }
                }

                return null
            }

            override fun onPostExecute(result: Void?) {
                if((found)&&(person!=null)) {
                    Log.d("countats exits notof", person?.name!!)
                    sendNotification(
                        "  contact Bot pour " + person?.name!!,
                        " reponse automatique vers le mail ${person!!.email} pour le ${person!!.name} ",
                        active
                    )
                }
            }


        }
        luncher.execute()
    }


    private fun sendNotification(titre: String, contenu: String, context: Context) {

        /*if (intent.action === "dz.esi.demobroadcast") {
            titre = "Un titre"
            contenu = "Diffusion"
        } else {
            titre = "Un appel"
            contenu = "Appel suivi"
        }*/


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            mChannel.description = " this channel is for contact response app "


            notificationManager.createNotificationChannel(mChannel)

            val noti = Notification.Builder(context, channelId)
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(false)

                .build()
            notificationManager.notify(0, noti)
            Log.d("notif", "sent1")

        } else {

            val noti = Notification.Builder(context)
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(false)

                .build()
            notificationManager.notify((1..102).shuffled().first(), noti)
            Log.d("notif", "sent2")


        }

    }
}