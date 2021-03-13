package com.example.thingder.data.usecases

import android.net.Uri
import com.example.thingder.domain.usecases.ICreateItemUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateItemUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ICreateItemUseCase {

    override suspend fun createThing(title: String, uri: Uri): Boolean {
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
}