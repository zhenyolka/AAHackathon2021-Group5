package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.ICreateItemUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateItemUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ICreateItemUseCase {

    override suspend fun createThing(thing: Thing): Boolean {
        db.collection(FireConstants.COLLECTION_THINGS)
            .document(auth.currentUser.uid + thing.title)
            .set(
                hashMapOf(
                    FireConstants.KEY_USER_ID to auth.currentUser.uid,
                    FireConstants.KEY_THING_TITLE to thing.title
                )
            )
        return true
    }
}