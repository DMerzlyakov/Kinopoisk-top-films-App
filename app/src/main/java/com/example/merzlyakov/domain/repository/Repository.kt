package com.example.merzlyakov.domain.repository

import retrofit2.Call
import com.example.merzlyakov.data.api.dto.FilmDescribeDTO
import com.example.merzlyakov.data.api.dto.FilmListDTO

interface Repository {

//  Запрос на получение списка популярных фильмов по API
    fun getFilmListByApi() : Call<FilmListDTO>

//  Запрос на получение подробной информации о фильме по API
    fun getFilmByApi(id : Int): Call<FilmDescribeDTO>
}