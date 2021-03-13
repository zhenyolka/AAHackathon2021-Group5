package com.example.thingder.data.usecases

import android.net.Uri
import android.util.Log
import com.example.thingder.domain.usecases.ICreateItemUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CreateItemUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage
) : ICreateItemUseCase {

    override suspend fun createThing(title: String, uri: Uri): Boolean {
        val downloadUri = uploadImage(uri, title)
        Log.d("LOAD_TAG", "downloadUri: $downloadUri")

        db.collection(FireConstants.COLLECTION_THINGS)
            .document(auth.currentUser.uid + title)
            .set(
                hashMapOf(
                    FireConstants.KEY_THING_USER_ID to auth.currentUser.uid,
                    FireConstants.KEY_THING_TITLE to title,
                    FireConstants.KEY_THING_IMAGE_ID to downloadUri
                )
            )
        return true
    }

    private suspend fun uploadImage(uri: Uri, title: String): String = withContext(Dispatchers.IO) {
        val storagePathRef = storage.reference.child("images")
        val imageName = auth.currentUser!!.uid + title
        val imageRef = storagePathRef.child(imageName)
        val uploadTask = imageRef.putFile(uri)

        uploadTask.await()
        val image = imageRef.downloadUrl.await()

        return@withContext image.toString()
    }
}