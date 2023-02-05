package com.example.merzlyakov.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.merzlyakov.R
import com.example.merzlyakov.databinding.ActivityMainBinding
import com.example.merzlyakov.presentation.views.KinopoiskFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainHolder, KinopoiskFragment.newInstance())
            .commit()
    }
}