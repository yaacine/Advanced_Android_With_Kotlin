package com.example.exo2_persistance

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

     lateinit var adapter :MyListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MyListAdapter(this, R.layout.row, DataManager.interventionsList)
        val fileName = cacheDir.absolutePath+"/Interventions.json"
       // val dataManager = DataManager()
        DataManager.filePath = fileName
        println(DataManager.interventionsList.toString())

      //  var inter= Intervention("1", LocalDate.now().toString()  ,"Yacine", "Fuite" )
       // dataManager.interventionsList.add(inter)

       // dataManager.writeListToJSONFile(fileName)
        DataManager.readJSONfromFile()


        listView.adapter =  adapter
        // Get the file Location and name where Json File are get stored


        //adding item
        addIntervention.setOnClickListener {
            println("new date ====> "+editTextDate.text.toString().replace("/", "-"))
            var entredDate = editTextDate.text.toString().replace("/", "-")
            var elements = entredDate.split("-")
            var correctDate = elements[2]+"-"+elements[1]+"-"+elements[0]
            println("correctedate ===>" +correctDate)
            println("plombier ===>" +editText.text.toString() )
            val rnds = (0..10000).random()
            val newItem = Intervention(numero = rnds.toString(), nomPmobier =  editText.text.toString() , date = correctDate.toString(), type = typeIntervantion.text.toString() )
            DataManager.interventionsList.add(newItem)

            listView.adapter =  adapter
            adapter.notifyDataSetChanged()
            DataManager.writeListToJSONFile()


            editText.text.clear()
            editTextDate.text.clear()
            typeIntervantion.text.clear()
        }

    }
}
