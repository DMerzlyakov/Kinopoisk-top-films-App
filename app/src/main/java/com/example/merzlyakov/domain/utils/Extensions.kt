package com.example.merzlyakov.domain.utils

import com.example.merzlyakov.common.Constants
import com.example.merzlyakov.data.api.KinopoiskUnofficialApi
import com.example.merzlyakov.data.repository.RepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create



object DataHelper {
    fun getRepository(): RepositoryImpl {
        return RepositoryImpl(getInstance())
    }
    private fun getInstance(): KinopoiskUnofficialApi {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KinopoiskUnofficialApi::class.java)
    }
}

