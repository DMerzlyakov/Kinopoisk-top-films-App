package com.example.merzlyakov.presentation.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.merzlyakov.data.api.dto.FilmDescribeDTO
import com.example.merzlyakov.data.api.dto.getFilmDescribe
import com.example.merzlyakov.domain.model.FilmDescribe
import com.example.merzlyakov.domain.utils.DataHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailViewModel(application: Application) : AndroidViewModel(application) {

    val liveFilmsList = MutableLiveData<FilmDescribe>()
    val liveError = MutableLiveData<String>()
    private val repo = DataHelper.getRepository(getApplication<Application>().applicationContext)

    // Асинхронно обрабатываем отправленный запрос получения детальной информации по фильму
    // Обрабатываем исключения
    fun loadDataByApi(id: Int) {
        repo.getFilmByApi(id).enqueue(object : Callback<FilmDescribeDTO> {
            override fun onResponse(
                call: Call<FilmDescribeDTO>,
                response: Response<FilmDescribeDTO>
            ) {
                try {
                    liveFilmsList.value = (response.body() as FilmDescribeDTO).getFilmDescribe()
                    Log.d("API_CONNECTION", "Успешный запрос получения описания фильма")
                } catch (e: NullPointerException) {
                    liveError.value = "Сервер временно не отвечает. Попробуйте позже"
                    Log.e("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }

            override fun onFailure(call: Call<FilmDescribeDTO>, t: Throwable) {
                liveError.value = "Проверьте ваше интернет соединение"
                Log.e("API_CONNECTION", "Ошибка выполнения запроса из сети")
            }
        })
    }
}