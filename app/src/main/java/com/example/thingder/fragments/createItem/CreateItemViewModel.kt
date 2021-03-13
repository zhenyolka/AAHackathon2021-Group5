package com.example.thingder.fragments.createItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.ICreateItemUseCase
import kotlinx.coroutines.launch
import java.util.*

class CreateItemViewModel(private val iCreateItemUseCase: ICreateItemUseCase) : ViewModel() {

    private var _isCreateSuccess = MutableLiveData<Boolean>()
    val isCreateSuccess: LiveData<Boolean> get() = _isCreateSuccess

    fun createThing(title: String) {
        viewModelScope.launch {
            val result = iCreateItemUseCase.createThing(Thing(Random().nextInt(), title))
            _isCreateSuccess.postValue(result)
        }

    }
}