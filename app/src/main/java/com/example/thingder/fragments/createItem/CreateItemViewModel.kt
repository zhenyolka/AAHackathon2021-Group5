package com.example.thingder.fragments.createItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateItemViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private var _isCreateSuccess = MutableLiveData(false)
    val isCreateSuccess: LiveData<Boolean> get() = _isCreateSuccess

    fun createThing(title: String) {
        if (title.isNotEmpty()) {
            _text.value = "Created thing with title: $title"
            _isCreateSuccess.value = true
        } else {
            _isCreateSuccess.value = false
        }
    }
}