package com.example.exo6_room.database

import androidx.room.*
import java.time.LocalDate
import java.util.*


@Entity(primaryKeys  = [ "groupe", "date", "heure_debut"],tableName = "seance")

data class Seance(

    val groupe:String ,
    val date:String ,
    val heure_debut:Int ,
    val heure_fin:Int ,
    val module:String ,
    val salle:Int ,
    val enseignant : String
)