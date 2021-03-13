package com.example.thingder.fragments.main

import androidx.lifecycle.*
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.tinder.IDislikeThingUseCase
import com.example.thingder.domain.usecases.tinder.IFetchNearbyThingUseCase
import com.example.thingder.domain.usecases.tinder.ILikeThingUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val fetchNearbyThingUseCase: IFetchNearbyThingUseCase,
                    private val likeThingUseCase: ILikeThingUseCase,
                    private val dislikeThingUseCase: IDislikeThingUseCase): ViewModel() {
    val things: LiveData<List<Thing>> = fetchNearbyThingUseCase.fetch().asLiveData()

    fun onSwipeRight(thing: Thing) {
        viewModelScope.launch {
            likeThingUseCase.like(thing)
        }
    }

    fun onSwipeLeft(thing: Thing) {
        viewModelScope.launch {
            dislikeThingUseCase.dislike(thing)
        }
    }
}