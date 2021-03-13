package com.example.thingder.fragments.likedThings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchLikedThingsUseCase

class LikedThingsViewModel(private val fetchLikedThingsUseCase: IFetchLikedThingsUseCase): ViewModel() {
    private val _things = MutableLiveData<List<Thing>>()
    val things: LiveData<List<Thing>> get() = _things

    init {
        _things.postValue(fetchLikedThingsUseCase.fetch().asLiveData().value)
    }

    fun deleteItem(thing: Thing) {
        // TODO: implement function
    }
}