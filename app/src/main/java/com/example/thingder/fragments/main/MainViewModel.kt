package com.example.thingder.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thingder.domain.entities.Thing

class MainViewModel: ViewModel() {
    private val _things = MutableLiveData<List<Thing>>()
    val things: LiveData<List<Thing>> get() = _things

    init {
        // TODO: replace with API call
        _things.postValue(listOf(
            Thing(
                id = 1,
                title = "Brick"
            ),
            Thing(
                id = 2012,
                title = "End of The World Button"
            ),
            Thing(
                id = 42,
                title = "Universe"
            )
        ))
    }

    fun onSwipeRight(position: Int, thing: Thing) {

    }

    fun onSwipeLeft(position: Int, thing: Thing) {

    }

    fun refresh() {
        _things.postValue(listOf(
            Thing(
                id = 1,
                title = "Brick"
            ),
            Thing(
                id = 2012,
                title = "End of The World Button"
            ),
            Thing(
                id = 42,
                title = "Universe"
            )
        ))
    }
}