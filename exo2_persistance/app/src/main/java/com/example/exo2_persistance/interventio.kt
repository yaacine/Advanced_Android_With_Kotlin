package com.example.exo2_persistance
import com.google.gson.Gson
import java.time.LocalDate
import java.util.*

var gson = Gson()

class Intervention (
    var numero: String,
    var date : String,
    var nomPmobier:String,
    var type:String
)
{
    fun toJson(): String? {
        return  gson.toJson(this)
    }

}

