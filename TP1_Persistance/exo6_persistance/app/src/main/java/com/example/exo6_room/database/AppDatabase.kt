package com.example.exo6_room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Seance::class],version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun SeanceDao():SeanceDao

    companion object{
        private var instance : AppDatabase? = null

         fun getInstance(context: Context) : AppDatabase? {
            if(instance==null){
                instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"seance.db").build()
            }

                return instance

        }
        fun destroyInstance(){
            instance = null ;
        }
    }

}