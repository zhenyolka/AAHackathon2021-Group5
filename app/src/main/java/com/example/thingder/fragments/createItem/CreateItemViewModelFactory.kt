package com.example.thingder.fragments.createItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecases.ICreateItemUseCase

class CreateItemViewModelFactory(private val iCreateItemUseCase: ICreateItemUseCase): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        CreateItemViewModel::class.java -> CreateItemViewModel(iCreateItemUseCase)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}