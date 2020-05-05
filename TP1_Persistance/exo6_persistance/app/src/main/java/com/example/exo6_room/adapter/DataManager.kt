package com.example.exo6_room.adapter


import android.util.Log
import com.example.exo6_room.database.Seance
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File


class  DataManager {



    companion object{


        var filePath :String =""
        var interventionsList = arrayListOf<Seance>()


        fun readJSONfromFile(): java.util.ArrayList<*>? {
            var gson = Gson()
            val bufferedReader: BufferedReader = File((filePath)).bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            var list:List<Seance> = gson.fromJson(inputString, Array<Seance>::class.java).toList()
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