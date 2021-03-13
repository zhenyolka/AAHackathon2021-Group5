package com.example.thingder.fragments.myThings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecase.IFetchMyThings

class MyThingsViewModel(private val fetchMyThings: IFetchMyThings) : ViewModel() {
    val things: LiveData<List<Thing>> = fetchMyThings.fetch().asLiveData()

    fun deleteItem(thing: Thing) {
        // TODO: implement function
    }
}