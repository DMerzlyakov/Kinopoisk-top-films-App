package com.example.merzlyakov.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.merzlyakov.R
import com.example.merzlyakov.databinding.ItemFilmBinding
import com.example.merzlyakov.domain.model.FilmInfo
import com.squareup.picasso.Picasso

// Адаптер для RecyclerView
class FilmsListAdapter : ListAdapter<FilmInfo, ViewHolder>(CustomComparator()) {

    var onFilmItemClickListener: ((FilmInfo) -> Unit)? = null
    var onFilmItemLongClickListener: ((FilmInfo) -> Unit)? = null

    // Подгружаем нашу модель в элемент списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ViewHolder(view)
    }

    // Объявляем значения в определённом элементы
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmItem = getItem(position)
        holder.view.setOnClickListener {
            onFilmItemClickListener?.invoke(filmItem)
        }
        holder.view.setOnLongClickListener {
            onFilmItemLongClickListener?.invoke(filmItem)
            true
        }
        holder.bind(filmItem)
    }
}


// Компаратор для сравнения изменений элементов
class CustomComparator : DiffUtil.ItemCallback<FilmInfo>() {
    override fun areItemsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean {
        return oldItem == newItem
    }

}

// Холдер для изменения данных
class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemFilmBinding.bind(view)

    fun bind(item: FilmInfo) {
        with(binding) {
            titleFilm.text = item.title
            descriptionFilm.text = "${item.genre} (${item.year})"

            Picasso.get().load(item.poster).into(imgFilm)

            if (item.favorite) {
                favorityIcon.visibility = ImageView.VISIBLE
            } else {
                favorityIcon.visibility = ImageView.INVISIBLE
            }
        }
    }
}