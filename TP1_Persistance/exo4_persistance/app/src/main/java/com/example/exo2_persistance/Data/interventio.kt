package com.example.exo4_persistance
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.time.LocalDate
import java.util.*

var gson = Gson()

@Entity
class Intervention (
    @PrimaryKey var numero: String,
    @ColumnInfo (name = "date") var date : String,
    @ColumnInfo(name = "nomPlombier") var nomPmobier:String,
    @ColumnInfo(name = "type") var type:String
)


