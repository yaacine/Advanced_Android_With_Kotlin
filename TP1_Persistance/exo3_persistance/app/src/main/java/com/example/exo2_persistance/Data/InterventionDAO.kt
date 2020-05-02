package com.example.exo2_persistance.Data

import androidx.room.*
import com.example.exo3_persistance.Intervention

@Dao
interface InterventionDAO {

    @Query("SELECT * FROM intervention")
    fun getAll():  List<Intervention>

    @Query("SELECT * FROM intervention WHERE date LIKE :date")
    fun findByDate(date: String): Intervention

    @Insert
    fun insertAll(vararg interv: Intervention)

    @Delete
    fun delete(interv: Intervention)

    @Update
    fun updateTodo(vararg interv: Intervention)
}