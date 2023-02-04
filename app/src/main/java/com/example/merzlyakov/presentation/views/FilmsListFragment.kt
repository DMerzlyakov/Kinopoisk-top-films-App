package com.example.merzlyakov.presentation.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.merzlyakov.databinding.FragmentFilmsListBinding
import com.example.merzlyakov.databinding.KinopoiskFragmentBinding
import com.example.merzlyakov.presentation.view_models.KinopoiskViewModel

class FilmsListFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = FilmsListFragment()
    }

//    private lateinit var viewModel: KinopoiskViewModel
    private lateinit var binding: FragmentFilmsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}