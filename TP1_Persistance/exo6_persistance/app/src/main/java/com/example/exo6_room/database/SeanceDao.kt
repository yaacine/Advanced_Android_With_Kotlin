package com.example.exo6_room.database

import androidx.room.*

@Dao
interface SeanceDao {



    @Query("select * from seance ")
    fun getAll():List<Seance>

    @Query("select date from seance")
    fun getAllAvailableDate(): List<String>

    @Query("select * from seance where date between :dateMin and :dateMax ")
    fun getBetweenDates(dateMin:String ,dateMax:String):List<Seance>

    @Query("select * from seance where groupe like '%' || :groupe || '%'")
    fun getByGroup(groupe: String):List<Seance>

    @Query("select * from seance where module like '%' || :module || '%'")
    fun getByModule(module:String):List<Seance>

    @Query("select * from seance where enseignant like '%' ||  :ens || '%'")
    fun getByProf(ens:String):List<Seance>

    @Query("select * from seance where salle = :salle")
    fun getBySalle(salle:Int):List<Seance>

    @Query("select * from seance where date like '%' || :date || '%'")
    fun getByDate(date:String):List<Seance>

    @Insert
    fun inserer(seance: Seance)

    @Update
    fun modifier(seance:Seance)

    @Delete
    fun supprimer(seance: Seance)

    @Query("delete from seance")
    fun reset()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract fun insertAll(list: ArrayList<Seance>)

}