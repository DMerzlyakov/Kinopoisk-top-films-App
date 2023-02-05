package com.example.merzlyakov.data.api


import com.example.merzlyakov.common.Constants
import com.example.merzlyakov.data.api.dto.FilmDescribeDTO
import com.example.merzlyakov.data.api.dto.FilmListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface KinopoiskUnofficialApi {

    @Headers("X-API-KEY: ${Constants.API_KEY}")
    @GET("films/{id}")
    fun getFilm(@Path("id") id: Int) : Call<FilmDescribeDTO>

    @Headers("X-API-KEY: ${Constants.API_KEY}")
    @GET("films/top?type=TOP_100_POPULAR_FILMS")
    fun getFilmList() : Call<FilmListDTO>

}