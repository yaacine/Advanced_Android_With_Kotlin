package com.example.exo4_persistance

import android.util.Log
import com.example.exo2_persistance.Data.AppDatabase
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File


 class  DataManager {



     companion object{


        lateinit var dbReference:AppDatabase
        var interventionsList = arrayListOf<Note>()


         fun getAll() {
            GlobalScope.launch {

                var myDataList = dbReference.interventionDao().getAll()
                interventionsList.addAll(myDataList)
            }

        }


        fun delete(){

        }
    }




}


