package com.example.thingder.fragments.likedThings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchLikedThingsUseCase

class LikedThingsViewModel(private val fetchLikedThingsUseCase: IFetchLikedThingsUseCase): ViewModel() {
    val things: LiveData<List<Thing>> = fetchLikedThingsUseCase.fetch().asLiveData()

    fun deleteItem(thing: Thing) {
        // TODO: implement function
    }
}