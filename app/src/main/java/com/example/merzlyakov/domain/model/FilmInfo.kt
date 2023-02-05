package com.example.merzlyakov.domain.model

data class FilmInfo(
    val id: Int,
    val title: String,
    val year: String,
    val poster: String,
    val genre: String,
    var favorite: Boolean = false
)
