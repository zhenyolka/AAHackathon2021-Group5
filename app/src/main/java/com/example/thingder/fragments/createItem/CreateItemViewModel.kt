package com.example.thingder.fragments.createItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateItemViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    fun createThing(title: String) {
        _text.value = "Created thing with title: $title"
    }

}