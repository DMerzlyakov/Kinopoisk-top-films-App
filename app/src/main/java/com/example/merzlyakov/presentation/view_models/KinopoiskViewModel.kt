package com.example.merzlyakov.presentation.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.merzlyakov.data.api.dto.FilmListDTO
import com.example.merzlyakov.data.api.dto.getFilmList
import com.example.merzlyakov.domain.model.FilmInfo
import com.example.merzlyakov.domain.utils.DataHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KinopoiskViewModel(application: Application) : AndroidViewModel(application) {

    val liveFilmsList = MutableLiveData<List<FilmInfo>>()
    val liveError = MutableLiveData<String>()
    private var repo = DataHelper.getRepository(getApplication<Application>().applicationContext)

    // Асинхронно обрабатываем отправленный запрос получения популярных фильмов
    // Обрабатываем исключения
    fun loadDataByApi() {
        repo.getFilmsListByApi().enqueue(object : Callback<FilmListDTO> {
            override fun onResponse(call: Call<FilmListDTO>, response: Response<FilmListDTO>) {
                try {
                    val data = (response.body() as FilmListDTO).getFilmList()
                    val ids = getFavoriteById()
                    data.map {
                        if (it.id in ids) {
                            it.favorite = true
                        }
                    }
                    liveFilmsList.value = data
                    Log.d("API_CONNECTION", "Успешный запрос получения списка фильмов")
                } catch (e: NullPointerException) {
                    liveError.value = "Сервер временно не отвечает. Попробуйте позже"
                    Log.e("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }

            override fun onFailure(call: Call<FilmListDTO>, t: Throwable) {
                liveError.value = "Проверьте ваше интернет соединение"
                Log.e("API_CONNECTION", "Ошибка выполнения запроса из сети")
            }
        })
    }

    // Получаем список id фильмов, которые в избранном
    private fun getFavoriteById(): List<Int> {
        return repo.getIdsFavorite()
    }

    // Загружаем из бд список избранных фильмов
    fun loadFavoriteFilms() {
        liveFilmsList.value = repo.getFavoriteFilmsByDao()
        Log.d("DATA_FROM_DB", "${liveFilmsList.value}")
    }

    // Добавляем в бд избранный фильм
    fun addFavourite(film: FilmInfo) {
        repo.addFavoriteFilmToDao(film) //, repo.getFilmByApi(film.id)
        liveFilmsList.value = editItemLiveData(film, true)
        Log.d("ADD_FAVORITE_TO_DB", "${film.id} - ${film.title}")
    }

    // Удаляем из бд избранный фильм
    fun deleteFavourite(film: FilmInfo) {
        repo.removeFavoriteFilmFromDao(film)
        liveFilmsList.value = editItemLiveData(film, false)
        Log.d("ADD_FAVORITE_TO_DB", "${film.id} - ${film.title}")
    }

    private fun editItemLiveData(film: FilmInfo, status: Boolean): List<FilmInfo> {
        return liveFilmsList.value!!.map {
            if (it.id == film.id) {
                it.copy(favorite = status)
            } else {
                it
            }
        }
    }
}
