package com.example.thingder

import android.app.Application
import androidx.lifecycle.ViewModelProvider

object Injection {
    private lateinit var viewModelFactory: MainViewModelFactory

    fun setup(app: Application) {
        viewModelFactory = MainViewModelFactory(app)
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }
}