package com.example.exo2_persistance.Data

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.exo4_persistance.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun interventionDao(): InterventionDAO
}