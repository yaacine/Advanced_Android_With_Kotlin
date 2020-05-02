package com.example.exo3_persistance

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.exo2_persistance.Data.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MyListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing the db
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "todo-list.db"
        ).build()
        DataManager.dbReference = db

        var myData = arrayListOf<Intervention>()

        var inter = Intervention("1", LocalDate.now().toString(), "Yacine", "Fuite")
       // DataManager.interventionsList.add(inter)


        GlobalScope.launch {
            var myDataList = db.interventionDao().getAll()
            DataManager.interventionsList.addAll(myDataList)
        }



        adapter = MyListAdapter(this, R.layout.row, DataManager.interventionsList)
        val fileName = cacheDir.absolutePath + "/Interventions2.json"
        // val dataManager = DataManager()

        println(DataManager.interventionsList.toString())

        //  var inter= Intervention("1", LocalDate.now().toString()  ,"Yacine", "Fuite" )
        // dataManager.interventionsList.add(inter)


        listView.adapter = adapter
        // Get the file Location and name where Json File are get stored


        //adding item
        addIntervention.setOnClickListener {
            println("new date ====> " + editTextDate.text.toString().replace("/", "-"))
            var entredDate = editTextDate.text.toString().replace("/", "-")
            var elements = entredDate.split("-")
            var correctDate = elements[2] + "-" + elements[1] + "-" + elements[0]
            println("correctedate ===>" + correctDate)
            println("plombier ===>" + editText.text.toString())
            val rnds = (0..10000).random()
            val newItem = Intervention(
                numero = rnds.toString(),
                nomPmobier = editText.text.toString(),
                date = correctDate.toString(),
                type = typeIntervantion.text.toString()
            )
            DataManager.interventionsList.add(newItem)

            listView.adapter = adapter

            GlobalScope.launch {
                db.interventionDao().insertAll(
                    Intervention(
                        numero = rnds.toString(),
                        nomPmobier = editText.text.toString(),
                        date = correctDate.toString(),
                        type = typeIntervantion.text.toString()
                    )
                )
                var myDataList = db.interventionDao().getAll()
                DataManager.interventionsList.clear()
                DataManager.interventionsList.addAll(myDataList)
                adapter.notifyDataSetChanged()
                DataManager.interventionsList?.forEach {
                    println(it)
                }
            }
            editText.text.clear()
            editTextDate.text.clear()
            typeIntervantion.text.clear()
        }

        searchDateBtn.setOnClickListener {

            val value = GlobalScope.launch {
                var entredDate = editTextDate.text.toString().replace("/", "-")
                var elements = entredDate.split("-")
                var correctDate = elements[2] + "-" + elements[1] + "-" + elements[0]

                var myDataList = db.interventionDao().findByDate(correctDate)
                DataManager.interventionsList.clear()
                DataManager.interventionsList.add(myDataList)


                DataManager.interventionsList?.forEach {
                    println(it)
                }
            }



            value.invokeOnCompletion {
                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    adapter.notifyDataSetChanged()
                    editText.text.clear()
                    editTextDate.text.clear()
                    typeIntervantion.text.clear()
                })
            }

        }

    }

}

