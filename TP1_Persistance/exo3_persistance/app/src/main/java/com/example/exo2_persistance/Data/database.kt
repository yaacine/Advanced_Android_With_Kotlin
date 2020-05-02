package com.example.exo2_persistance.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exo3_persistance.Intervention

@Database(entities = arrayOf(Intervention::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun interventionDao(): InterventionDAO
}