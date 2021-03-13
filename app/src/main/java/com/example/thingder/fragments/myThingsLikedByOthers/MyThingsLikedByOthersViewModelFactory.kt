package com.example.thingder.fragments.myThingsLikedByOthers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase

class MyThingsLikedByOthersViewModelFactory(private val fetchMyThingsUseCase: IFetchMyThingsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        MyThingsLikedByOthersViewModel::class.java -> {
            MyThingsLikedByOthersViewModel(fetchMyThingsUseCase)
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}