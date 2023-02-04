package com.example.merzlyakov.data.repository

import com.example.merzlyakov.data.api.KinopoiskUnofficialApi
import com.example.merzlyakov.data.api.dto.FilmDescribeDTO
import com.example.merzlyakov.data.api.dto.FilmListDTO
import com.example.merzlyakov.domain.repository.Repository
import retrofit2.Call

class RepositoryImpl (private val apiKino: KinopoiskUnofficialApi): Repository {
    override fun getFilmListByApi(): Call<FilmListDTO> {
        return apiKino.getFilmList()
    }

    override fun getFilmByApi(id: Int): Call<FilmDescribeDTO> {
        return apiKino.getFilm(id)
    }
}