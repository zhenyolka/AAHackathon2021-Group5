package com.example.thingder.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecases.tinder.IDislikeThingUseCase
import com.example.thingder.domain.usecases.tinder.IFetchNearbyThingUseCase
import com.example.thingder.domain.usecases.tinder.ILikeThingUseCase

class MainViewModelFactory(val fetchNearbyThingUseCase: IFetchNearbyThingUseCase,
                           val likeThingUseCase: ILikeThingUseCase,
                           val dislikeThingUseCase: IDislikeThingUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IFetchNearbyThingUseCase::class.java,
                ILikeThingUseCase::class.java,
                IDislikeThingUseCase::class.java)
            .newInstance(fetchNearbyThingUseCase, likeThingUseCase, dislikeThingUseCase)
    }
}

