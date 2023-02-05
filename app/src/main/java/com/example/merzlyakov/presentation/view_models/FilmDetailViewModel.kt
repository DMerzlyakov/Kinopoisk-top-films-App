package com.example.merzlyakov.presentation.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.merzlyakov.data.api.dto.FilmDescribeDTO
import com.example.merzlyakov.data.api.dto.FilmListDTO
import com.example.merzlyakov.data.api.dto.getFilmDescribe
import com.example.merzlyakov.data.api.dto.getFilmList
import com.example.merzlyakov.domain.model.FilmDescribe
import com.example.merzlyakov.domain.model.FilmInfo
import com.example.merzlyakov.domain.utils.DataHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailViewModel : ViewModel() {

    val liveFilmsList = MutableLiveData<FilmDescribe>()
    private val repo = DataHelper.getRepository()

    // Асинхронно обрабатываем отправленный запрос получения популярных фильмов
    // Обрабатываем исключения
    fun loadData(id: Int) {
        repo.getFilmByApi(id).enqueue(object : Callback<FilmDescribeDTO> {
            override fun onResponse(call: Call<FilmDescribeDTO>, response: Response<FilmDescribeDTO>) {
                try {
                    liveFilmsList.value = (response.body() as FilmDescribeDTO).getFilmDescribe()
                    Log.d(
                        "API_CONNECTION",
                        (response.body() as FilmDescribeDTO).toString()
                    )
                } catch (e: NullPointerException) {
                    Log.e("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }
            override fun onFailure(call: Call<FilmDescribeDTO>, t: Throwable) {
                Log.e("API_CONNECTION", "Ошибка выполнения запроса из сети")
            }
        })
    }
}