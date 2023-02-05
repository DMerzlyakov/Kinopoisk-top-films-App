package com.example.merzlyakov.data.repository

import com.example.merzlyakov.data.api.KinopoiskUnofficialApi
import com.example.merzlyakov.data.api.dto.FilmDescribeDTO
import com.example.merzlyakov.data.api.dto.FilmListDTO
import com.example.merzlyakov.data.database.Film
import com.example.merzlyakov.data.database.FilmDatabase
import com.example.merzlyakov.domain.model.FilmDescribe
import com.example.merzlyakov.domain.model.FilmInfo
import com.example.merzlyakov.domain.repository.Repository
import retrofit2.Call

class RepositoryImpl(
    private val apiKino: KinopoiskUnofficialApi,
    private val filmDatabase: FilmDatabase
) : Repository {
    //  Запрос на получение списка популярных фильмов по API
    override fun getFilmsListByApi(): Call<FilmListDTO> {
        return apiKino.getFilmList()
    }

    //  Запрос на получение подробной информации о фильме по API
    override fun getFilmByApi(id: Int): Call<FilmDescribeDTO> {
        return apiKino.getFilm(id)
    }

    //  Запрос на получение списка избранных фильмов из БД
    override fun getFavoriteFilmsByDao(): List<FilmInfo> {
        return filmDatabase.filmDao().getAll()
            .map { FilmInfo(it.uid, it.title, it.year, it.poster, it.genres, true) }
    }

    //  Запрос на получение списка избранных фильмов из БД
    override fun getFavoriteFilmDescribeByDao(id: Int): FilmDescribe {
        val film = filmDatabase.filmDao().getDescriptionById(id)
        return FilmDescribe(
            title = film.title,
            describe = film.describe,
            genres = film.genres.split(", "),
            country = film.countries.split(", "),
            poster = film.poster
        )
    }

    //  Запрос получение списка id избранных фильмов
    override fun getIdsFavorite(): List<Int> {
        return filmDatabase.filmDao().getIds()
    }

    //  Запрос на добавление избранного фильма в БД
    override fun addFavoriteFilmToDao(film: FilmInfo) { // , filmDescribe: FilmDescribe
        filmDatabase.filmDao().addFilm(
            Film(
                uid = film.id,
                title = film.title,
                genres = film.genre,
                year = film.year,
                poster = film.poster
            )
        )
    }

    //  Запрос на удаление избранного фильма из БД
    override fun removeFavoriteFilmFromDao(film: FilmInfo) {
        filmDatabase.filmDao().deleteFilm(
            Film(
                uid = film.id,
                title = film.title,
                genres = film.genre,
                year = film.year,
                poster = film.poster
            )
        )
    }
}