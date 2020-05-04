package com.example.exo2_persistance.Data

import androidx.room.*
import com.example.exo4_persistance.Note

@Dao
interface InterventionDAO {

    @Query("SELECT * FROM note")
    fun getAll():  List<Note>

    @Query("SELECT * FROM note WHERE date LIKE :date")
    fun findByDate(date: String): Note

    @Insert
    fun insertAll(vararg interv: Note)

    @Delete
    fun delete(interv: Note)

    @Update
    fun updateTodo(vararg interv: Note)
}