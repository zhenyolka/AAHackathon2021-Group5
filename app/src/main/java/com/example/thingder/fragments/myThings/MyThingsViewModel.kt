package com.example.thingder.fragments.myThings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecase.FetchMyThings

class MyThingsViewModel(private val fetchMyThings: FetchMyThings) : ViewModel() {
    private val _things = MutableLiveData<List<Thing>>()
    val things: LiveData<List<Thing>> = _things

    init {
        // TODO: replace with API call
        _things.postValue(fetchMyThings.fetch().asLiveData().value)
    }

    fun deleteItem(thing: Thing) {
        // TODO: implement function
    }
}