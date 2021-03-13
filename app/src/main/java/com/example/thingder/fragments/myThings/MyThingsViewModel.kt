package com.example.thingder.fragments.myThings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase

class MyThingsViewModel(private val fetchMyThings: IFetchMyThingsUseCase) : ViewModel() {
    val things: LiveData<List<Thing>> = fetchMyThings.fetch().asLiveData()

    fun deleteItem(thing: Thing) {
        // TODO: implement function
    }
}