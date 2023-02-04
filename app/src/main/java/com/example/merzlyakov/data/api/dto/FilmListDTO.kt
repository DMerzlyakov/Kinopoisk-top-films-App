package com.example.merzlyakov.data.api.dto

import com.example.merzlyakov.domain.model.FilmInfo

data class FilmListDTO(
    val films: List<Film>,
    val pagesCount: Int
)

fun FilmListDTO.getFilmList(): List<FilmInfo> {
    return films.map { it.getFilmInfo() }
}