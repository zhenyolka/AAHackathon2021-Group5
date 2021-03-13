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

    fun createThing(title: String, bitmap: Bitmap?) {
        viewModelScope.launch {
            val thing = Thing(Random().nextInt(), title)
            val result = iCreateItemUseCase.createThing(thing)
            _isCreateSuccess.postValue(result)
        }

    }

    private suspend fun uploadImage(bitmap: Bitmap?): String = withContext(Dispatchers.IO) {
        var downloadUri = ""
        if (bitmap != null) {
            val storage: FirebaseStorage = Firebase.storage

            val storageRef = storage.reference
            val imagesRef = storageRef.child("images")

            val imageName =
                Firebase.auth.currentUser!!.uid + (1 + (Math.random() * 2564).toInt())

            val imageRef = imagesRef.child(imageName)

            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageData = baos.toByteArray()

            val uploadTask = imageRef.putBytes(imageData)
            uploadTask
                .addOnFailureListener {
                    // Handle unsuccessful uploads
                    Log.d("LOAD_TAG", "uploadTask Failure")
                }
                .addOnSuccessListener { taskSnapshot ->
                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                    Log.d("LOAD_TAG", "taskSnapshot ${taskSnapshot.metadata?.sizeBytes}")

                }
                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    imageRef.downloadUrl
                }
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        downloadUri = task.result.toString()
                        Log.d("LOAD_TAG", "downloadUri $downloadUri")
                    } else {
                        // Handle failures
                        Log.d("LOAD_TAG", "downloadUri Failure")
                    }
                }
        }
        return@withContext downloadUri
    }
}