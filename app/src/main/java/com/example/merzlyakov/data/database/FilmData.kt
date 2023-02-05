package com.example.merzlyakov.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmData(
    @PrimaryKey
    val uid: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "describe")
    val describe: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "countries")
    val countries: String,

    @ColumnInfo(name = "year")
    val year: String,

    @ColumnInfo(name = "poster")
    val poster: String,
)
