package com.example.merzlyakov.domain.repository

import retrofit2.Call
import com.example.merzlyakov.data.api.dto.FilmDescribeDTO
import com.example.merzlyakov.data.api.dto.FilmListDTO
import com.example.merzlyakov.data.database.Film
import com.example.merzlyakov.data.database.FilmData
import com.example.merzlyakov.domain.model.FilmDescribe
import com.example.merzlyakov.domain.model.FilmInfo

interface Repository {

    //  Запрос на получение списка популярных фильмов по API
    fun getFilmsListByApi() : Call<FilmListDTO>

    //  Запрос на получение подробной информации о фильме по API
    fun getFilmByApi(id : Int): Call<FilmDescribeDTO>

    //  Запрос на получение списка избранных фильмов из БД
    fun getFavoriteFilmsByDao(): List<FilmInfo>

    //  Запрос на получение списка избранных фильмов из БД
    fun getFavoriteFilmDescribeByDao(id: Int): FilmDescribe

    //  Запрос на добавление избранного фильма в БД
    fun addFavoriteFilmToDao(film: FilmInfo) // TODO(Добавлять ещё и подробную информацию)


    //  Запрос на удаление избранного фильма из БД
    fun removeFavoriteFilmFromDao(film: FilmInfo)

    //  Запрос получение списка id избранных фильмов
    fun getIdsFavorite(): List<Int>
}