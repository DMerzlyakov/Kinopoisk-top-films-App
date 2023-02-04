package com.example.merzlyakov.presentation.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.merzlyakov.data.api.dto.FilmListDTO
import com.example.merzlyakov.data.api.dto.getFilmList
import com.example.merzlyakov.domain.model.FilmInfo
import com.example.merzlyakov.domain.utils.DataHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KinopoiskViewModel : ViewModel() {

    val liveFilmsList = MutableLiveData<List<FilmInfo>>()
    private val repo = DataHelper.getRepository()


    // Асинхронно обрабатываем отправленный запрос получения популярных фильмов
    // Обрабатываем исключения
    fun updateData() {
        repo.getFilmListByApi().enqueue(object : Callback<FilmListDTO> {
            override fun onResponse(call: Call<FilmListDTO>, response: Response<FilmListDTO>) {
                try {
                    liveFilmsList.value = (response.body() as FilmListDTO).getFilmList()
                    Log.d(
                        "API_CONNECTION",
                        (response.body() as FilmListDTO).toString()
                    )
                } catch (e: NullPointerException) {
                    Log.e("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }

            override fun onFailure(call: Call<FilmListDTO>, t: Throwable) {
                Log.e("API_CONNECTION", "Ошибка выполнения запроса из сети")
            }
        })
    }

}