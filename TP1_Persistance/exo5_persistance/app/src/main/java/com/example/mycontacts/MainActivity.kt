package com.example.mycontacts

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var data: MutableList<ContactData> =  ArrayList()

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


        btn_read_contact.setOnClickListener {
            //We need to verify permission


            val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
            if (contacts != null) {
                while (contacts.moveToNext()){
                    val name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val obj = ContactData()
                    obj.name = name
                    obj.number = number


                    data.add(obj)
                }

                (contact_list.adapter as ContactAdapter).notifyDataSetChanged()
                contacts.close()
            }


        }

    }
}
