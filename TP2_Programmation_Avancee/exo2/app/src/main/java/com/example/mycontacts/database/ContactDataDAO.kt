package com.example.mycontacts.database


import androidx.room.*

@Dao
interface ContactDataDAO {


    @Query("delete from contacts")
    fun reset()

    @Insert
    fun insert(contactData: ContactData)

    @Query("select * from contacts where send=1")
    fun getActiveContacts():List<ContactData>

    @Query("update contacts set send=1 where number=:number")
    fun activateNumber(number: String)

    @Query("update contacts set send=0 where number=:number")
    fun desActivateNumber(number: String)


    @Query("select * from contacts")
    fun getAllContacts():List<ContactData>
}