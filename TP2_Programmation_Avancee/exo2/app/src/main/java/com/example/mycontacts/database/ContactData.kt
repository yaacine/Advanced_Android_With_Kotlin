package com.example.mycontacts.database
import androidx.room.*

@Entity(primaryKeys  = ["number"],tableName = "contacts")
data class ContactData(


        val name :String ,
        val number :String ,
        val email : String ,
        val send :  Boolean



)
