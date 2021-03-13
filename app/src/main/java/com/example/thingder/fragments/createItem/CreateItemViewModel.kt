package com.example.thingder.fragments.createItem

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thingder.domain.usecases.ICreateItemUseCase
import kotlinx.coroutines.launch

class CreateItemViewModel(private val iCreateItemUseCase: ICreateItemUseCase) : ViewModel() {
    private var _isCreateSuccess = MutableLiveData<Boolean>()
    val isCreateSuccess: LiveData<Boolean> get() = _isCreateSuccess

    private var _imageUri = MutableLiveData<Uri>()
    val imageUri: LiveData<Uri> get() = _imageUri

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }

    fun createThing(title: String) {
        viewModelScope.launch {
            val uri = _imageUri.value
            if (uri == null || title.isBlank()) {
                _isCreateSuccess.postValue(false)
            } else {
                val result = iCreateItemUseCase.createThing(title, uri)
                _isCreateSuccess.postValue(result)
            }
        }
    }
}