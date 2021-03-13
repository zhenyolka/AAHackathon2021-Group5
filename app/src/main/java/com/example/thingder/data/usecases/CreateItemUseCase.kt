package com.example.thingder.data.usecases

import android.net.Uri
import android.util.Log
import com.example.thingder.domain.usecases.ICreateItemUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateItemUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) : ICreateItemUseCase {

    override suspend fun createThing(title: String, uri: Uri): Boolean {
        val downloadUrl = uploadImage(uri)

        db.collection(FireConstants.COLLECTION_THINGS)
            .document(auth.currentUser.uid + title)
            .set(
                hashMapOf(
                    FireConstants.KEY_USER_ID to auth.currentUser.uid,
                    FireConstants.KEY_THING_TITLE to title
                )
            )
        return true
    }

    private suspend fun uploadImage(uri: Uri): String = withContext(Dispatchers.IO) {
        val storagePathRef = storage.reference.child("images")
        val imageName = auth.currentUser!!.uid + (1 + (Math.random() * 2564).toInt())
        val imageRef = storagePathRef.child(imageName)
        val uploadTask = imageRef.putFile(uri)
        var downloadUri = ""
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    Log.d("LOAD_TAG", "exception: ${it.message}")
                }
            }
            storagePathRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                downloadUri = task.result.toString()
                Log.d("LOAD_TAG", "downloadUri: $downloadUri")
            } else {
                Log.d("LOAD_TAG", "downloadUri Failure")
            }
        }
        return@withContext downloadUri
    }
}