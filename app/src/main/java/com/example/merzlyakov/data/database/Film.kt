package com.example.merzlyakov.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film (
    @PrimaryKey
    val uid: Int,

    @ColumnInfo(name="title")
    val title: String,

    @ColumnInfo(name="genres")
    val genres: String,

    @ColumnInfo(name="poster")
    val poster: String,

    @ColumnInfo(name="year")
    val year: String,
)