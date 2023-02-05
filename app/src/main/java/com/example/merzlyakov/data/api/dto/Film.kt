package com.example.merzlyakov.data.api.dto

import com.example.merzlyakov.domain.model.FilmInfo

data class Film(
    val countries: List<Country>,
    val filmId: Int,
    val filmLength: String,
    val genres: List<Genre>,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String,
    val ratingChange: Any,
    val ratingVoteCount: Int,
    val year: String
)

fun Film.getFilmInfo(): FilmInfo {
    return FilmInfo(
        id = filmId,
        title = nameRu,
        year = year,
        poster = posterUrlPreview,
        genre = genres.firstOrNull()?.genre ?: "Unknown"
    )
}