package com.example.merzlyakov.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Film::class, FilmData::class], version = 1)
abstract class FilmDatabase :RoomDatabase(){
    abstract fun filmDao(): FilmDao
}