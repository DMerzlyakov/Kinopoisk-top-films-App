package com.example.merzlyakov.domain.utils

import android.content.Context
import androidx.room.Room
import com.example.merzlyakov.common.Constants
import com.example.merzlyakov.data.api.KinopoiskUnofficialApi
import com.example.merzlyakov.data.database.FilmDatabase
import com.example.merzlyakov.data.repository.RepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataHelper {

    // Инициализируем репозиторий
    fun getRepository(context: Context): RepositoryImpl {
        return RepositoryImpl(getInstanceRetrofit(), getInstanceRoom(context))
    }

    // Инициализируем Retrofit
    private fun getInstanceRetrofit(): KinopoiskUnofficialApi {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KinopoiskUnofficialApi::class.java)
    }

    // Инизиализируем Room
    private fun getInstanceRoom(context: Context) = Room.databaseBuilder(
        context, FilmDatabase::class.java, "kinopoisk"
    ).allowMainThreadQueries().build()
}

