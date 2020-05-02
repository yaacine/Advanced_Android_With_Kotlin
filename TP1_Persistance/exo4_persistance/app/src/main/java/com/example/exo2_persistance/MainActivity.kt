package com.example.exo4_persistance

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




        GlobalScope.launch {
            var myDataList = db.interventionDao().getAll()
            DataManager.interventionsList.addAll(myDataList)
        }







        }

    }



