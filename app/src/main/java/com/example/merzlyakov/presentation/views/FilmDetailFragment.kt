package com.example.merzlyakov.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.merzlyakov.MainActivity
import com.example.merzlyakov.databinding.FilmDetailFragmentBinding
import com.example.merzlyakov.presentation.view_models.FilmDetailViewModel
import com.squareup.picasso.Picasso


class FilmDetailFragment : Fragment() {

    companion object {
        fun newInstance(id: Int) = FilmDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("id", id)
            }
        }
    }

    private val viewModel: FilmDetailViewModel by activityViewModels()
    private lateinit var binding: FilmDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilmDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)
        binding.titleFilmData.text = arguments?.getString("title") ?: "Unknown"
        loadDescribe()
        viewModel.liveFilmsList.observe(viewLifecycleOwner) {
            with(binding) {
                Picasso.get().load(it.poster).resize(310, 450).into(bigPoster)
                titleFilmData.text = it.title
                descriptionFilmData.text = it.describe
                genreFilmData.text =
                    "${it.genres.joinTo(StringBuilder("Жанры: "), separator = ", ")}"
                countryFilmData.text =
                    "${it.country.joinTo(StringBuilder("Страны: "), separator = ", ")}"
            }
        }

        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }


    }

    private fun loadDescribe() {
        viewModel.loadData(requireArguments().getInt("id"))
    }


}