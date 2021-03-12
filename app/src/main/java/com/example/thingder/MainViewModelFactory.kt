package com.example.thingder

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val app: Application): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MainViewModel::class.java -> MainViewModel(app)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}