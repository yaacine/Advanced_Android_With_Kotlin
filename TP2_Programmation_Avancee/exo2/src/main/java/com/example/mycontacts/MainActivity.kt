package com.example.mycontacts


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.database.AppDatabase
import com.example.mycontacts.database.ContactData
import com.example.mycontacts.database.ContactDataDAO
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var data: MutableList<ContactData> =  ArrayList()
    private var db: AppDatabase? = null
    private var dao: ContactDataDAO? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //val contactList : MutableList<ContactData> = ArrayList()

        contact_list.layoutManager = LinearLayoutManager(this)
        contact_list.adapter = ContactAdapter(this.data,this)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                100);
        }


        val SMS_PERMISSION_REQ_CODE_SUBMIT = 101
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECEIVE_SMS),
                SMS_PERMISSION_REQ_CODE_SUBMIT
            )
        }

        val REQUEST_READ_SMS_PERMISSION = 3004;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_SMS),
                REQUEST_READ_SMS_PERMISSION
            )
        }

        init()

        val i = Intent()
        i.action = "test"

        i.setClass(this, MessageReceiver::class.java)
        this.sendBroadcast(i)

        btn_read_contact.setOnClickListener {
            //We need to verify permission
            this.data.clear()

            var _id_mail =""
            var _id_phone =""


            var contacts2 = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,null,null,null)
            val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
            if ((contacts != null)&&(contacts2!=null)) {
                while ((contacts.moveToNext())){

                    if (contacts2 != null) {
                        while(contacts2.moveToNext()) {

                            _id_phone =contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                            _id_mail =contacts2.getString(contacts2.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID))

                            Log.d("phone________________",_id_phone)
                            Log.d("mail",_id_mail)


                            if(_id_mail==_id_phone){
                                val name =
                                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                                val number =
                                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                val email =
                                    contacts2.getString(contacts2.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))

                                val obj =
                                    ContactData(name,number,email,false)

                                data.add(obj)

                            }


                        }
                    }
                    contacts2 =   contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,null,null,null) ;
                }


                val active = this ;
                val luncher = @SuppressLint("StaticFieldLeak")
                object : AsyncTask<Void, Void, Void>() {

                    override fun doInBackground(vararg params: Void?): Void? {

                        //in background
                        active.dao?.reset()
                        for (item in active.data) {
                            active.dao?.insert(item)
                        }

                        return null
                    }

                    override fun onPostExecute(result: Void?) {
                        showToast(active,"new  data saved and synchronized")
                    }


                }
                luncher.execute()


                (contact_list.adapter as ContactAdapter).notifyDataSetChanged()

                contacts.close()
                contacts2?.close()
            }


        }

    }

    fun init() {
        var activ = this
        val luncher = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                activ. db = AppDatabase.getInstance(activ)
                activ. dao =activ.db?.contactDao()

                //activ.data = activ.dao?.getAllContacts() as MutableList<ContactData>
                activ.data.clear()
                activ.data.addAll( activ.dao?.getAllContacts() as MutableList<ContactData>)


                return null
            }

            override fun onPostExecute(result: Void?) {


                (contact_list.adapter as ContactAdapter).notifyDataSetChanged()
                showToast(this@MainActivity," data reloaded from db")
            }
        }
        luncher.execute()
    }
    fun showToast(ctx: Context, message: CharSequence) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
    }

}
