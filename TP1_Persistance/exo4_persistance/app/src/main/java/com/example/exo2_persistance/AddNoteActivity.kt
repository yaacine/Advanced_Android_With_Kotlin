package com.example.exo4_persistance

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.exo4_persistance.DataManager
import com.example.exo4_persistance.Note
import com.example.exo4_persistance.R
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddNoteActivity : AppCompatActivity() {

    val dbTable = "Notes"
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        try {
            val bundle: Bundle? = intent.extras
            if (bundle != null) {
                id = bundle.getInt("ID", 0)
            }
            if (id!=0){
                if (bundle != null) {
                    titleEt.setText(bundle.getString("name"))
                }
                if (bundle != null) {
                    descEt.setText(bundle.getString("des"))
                }
            }
        }catch (ex:Exception){}
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addFunc(view: View){

        // TODO : add new note

        val rnds = (0..10000).random()
        var newNote= Note(id=rnds, date = LocalDate.now().toString(), titile =titleEt.text.toString() , text =descEt.text.toString() , color = "red")

        GlobalScope.launch {
            DataManager.dbReference.interventionDao().insertAll(
                newNote
            )
        }.invokeOnCompletion {
          //  Toast.makeText(this, "Note is added", Toast.LENGTH_SHORT).show()
            finish()
        }


    }
}
