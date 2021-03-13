package com.example.thingder.fragments.createItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thingder.domain.usecases.ICreateItemUseCase

class CreateItemViewModel(iCreateItemUseCase: ICreateItemUseCase) : ViewModel() {

    private var _isCreateSuccess = MutableLiveData<Boolean>()
    val isCreateSuccess: LiveData<Boolean> get() = _isCreateSuccess

    fun createThing(title: String) {
        _isCreateSuccess.value = title.isNotEmpty()
    }
}