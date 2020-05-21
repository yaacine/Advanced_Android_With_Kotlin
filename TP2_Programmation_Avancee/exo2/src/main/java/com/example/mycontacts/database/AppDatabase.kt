package com.example.mycontacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactData::class],version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun contactDao():ContactDataDAO

    companion object{
        private var instance : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase? {
            if(instance==null){
                instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"contacts.db").build()
            }

            return instance

        }
        fun destroyInstance(){
            instance = null ;
        }
    }

}