package com.example.merzlyakov.domain.model

data class FilmDescribe(
    val title : String,
    val describe: String,
    val genres: List<String>,
    val country: List<String>,
    val poster: String
)
