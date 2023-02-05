package com.example.merzlyakov.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.merzlyakov.databinding.NoInternetFragmentBinding

// TODO(Страница с отображением информации о проблем с соединением)
class NoInternetFragment : Fragment() {

    companion object {
        fun newInstance() = NoInternetFragment()
    }

    private lateinit var binding: NoInternetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoInternetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}