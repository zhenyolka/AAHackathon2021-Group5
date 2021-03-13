package com.example.thingder.fragments.likedThings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecases.IFetchLikedThingsUseCase

class LikedThingsViewModelFactory(val arg: IFetchLikedThingsUseCase): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IFetchLikedThingsUseCase::class.java)
            .newInstance(arg)
    }
}