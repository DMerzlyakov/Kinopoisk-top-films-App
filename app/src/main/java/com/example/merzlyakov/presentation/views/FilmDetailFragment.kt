package com.example.merzlyakov.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.merzlyakov.databinding.FilmDetailFragmentBinding
import com.example.merzlyakov.presentation.dialogs.DialogUtil
import com.example.merzlyakov.presentation.view_models.FilmDetailViewModel
import com.squareup.picasso.Picasso


class FilmDetailFragment : Fragment() {

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

        binding.titleFilmData.text = arguments?.getString("title") ?: "Unknown"

        loadDescribe()

        viewModel.liveFilmsList.observe(viewLifecycleOwner) {
            with(binding) {
                if (statusBar.isVisible) {
                    statusBar.visibility = ProgressBar.INVISIBLE
                }
                Picasso.get().load(it.poster).resize(310, 450).into(bigPoster)
                titleFilmData.text = it.title
                descriptionFilmData.text = it.describe
                genreFilmData.text =
                    "${it.genres.joinTo(StringBuilder("Жанры: "), separator = ", ")}"
                countryFilmData.text =
                    "${it.country.joinTo(StringBuilder("Страны: "), separator = ", ")}"
            }
        }

        viewModel.liveError.observe(viewLifecycleOwner) {
            DialogUtil.errorDialog(requireContext(), it)
            // TODO(Fargment for bo Internet connection)
        }

        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    // Загрузка описания по сети
    private fun loadDescribe() {
        if (!binding.statusBar.isVisible) {
            binding.statusBar.visibility = ProgressBar.VISIBLE
        }
        viewModel.loadDataByApi(requireArguments().getInt("id"))

    }

    companion object {
        fun newInstance(id: Int) = FilmDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("id", id)
            }
        }
    }
}