package com.example.thingder.fragments.myThingsLikedByOthers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecases.IFetchMyThingsLikedByOthersUseCase

class MyThingsLikedByOthersViewModelFactory(private val fetchMyThingsLikedByOthersUseCase: IFetchMyThingsLikedByOthersUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MyThingsLikedByOthersViewModel::class.java -> {
            MyThingsLikedByOthersViewModel(fetchMyThingsLikedByOthersUseCase)
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}