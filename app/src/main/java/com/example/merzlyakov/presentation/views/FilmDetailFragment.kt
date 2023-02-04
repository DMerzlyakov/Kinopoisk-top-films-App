package com.example.merzlyakov.presentation.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.merzlyakov.presentation.view_models.FilmDetailViewModel
import com.example.merzlyakov.R
import com.example.merzlyakov.databinding.FilmDetailFragmentBinding
import com.example.merzlyakov.databinding.FragmentFilmsListBinding

class FilmDetailFragment : Fragment() {

    companion object {
        fun newInstance() = FilmDetailFragment()
    }

    private lateinit var viewModel: FilmDetailViewModel
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
    }

}