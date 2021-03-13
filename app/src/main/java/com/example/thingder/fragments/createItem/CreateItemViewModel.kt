package com.example.thingder.fragments.createItem

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.ICreateItemUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

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
            if (uri == null) {
                _isCreateSuccess.postValue(false)
            } else {
                val result = iCreateItemUseCase.createThing(title, uri)
                _isCreateSuccess.postValue(result)
            }
        }
    }
}