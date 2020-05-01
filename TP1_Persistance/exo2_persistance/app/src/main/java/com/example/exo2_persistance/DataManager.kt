package com.example.exo2_persistance

import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File


 class  DataManager {



     companion object{


        var filePath :String =""
        var interventionsList = arrayListOf<Intervention>()


        fun readJSONfromFile(): java.util.ArrayList<*>? {
            var gson = Gson()
            val bufferedReader: BufferedReader = File(filePath).bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            var list:List<Intervention> = gson.fromJson(inputString, Array<Intervention>::class.java).toList()
            print("this is th list ====>"+list.toString())
            this.interventionsList.addAll(list)
            return null
        }


        fun writeListToJSONFile(){
            var gson = Gson()
            var jsonString:String = gson.toJson(this.interventionsList)
            val file=File(filePath)
            file.writeText(jsonString)
        }
    }




}


