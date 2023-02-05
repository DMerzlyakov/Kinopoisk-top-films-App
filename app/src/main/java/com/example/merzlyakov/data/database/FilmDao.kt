package com.example.merzlyakov.data.database

import androidx.room.*

@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun getAll():List<Film>

    @Query("SELECT * FROM filmData WHERE uid =:id")
    fun getDescriptionById(id: Int): FilmData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilm(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFilm(film: FilmData)

    @Delete
    fun deleteFilm(film: Film)

    @Query("SELECT uid FROM film")
    fun getIds(): List<Int>
}