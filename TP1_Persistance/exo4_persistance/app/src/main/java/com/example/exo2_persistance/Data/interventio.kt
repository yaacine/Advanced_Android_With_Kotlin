package com.example.exo4_persistance
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.time.LocalDate
import java.util.*

var gson = Gson()

@Entity
class Note (

    @PrimaryKey(autoGenerate = true) var id: Int,

    @ColumnInfo (name = "date") var date : String,
    @ColumnInfo(name = "title") var titile:String,
    @ColumnInfo(name = "color") var color:String,
    @ColumnInfo(name = "text") var text:String
)


