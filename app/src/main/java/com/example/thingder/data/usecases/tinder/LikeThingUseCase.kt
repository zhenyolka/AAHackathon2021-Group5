package com.example.thingder.data.usecases.tinder

import com.example.thingder.data.usecases.FireConstants
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.tinder.ILikeThingUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LikeThingUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
): ILikeThingUseCase {

    override suspend fun like(thing: Thing) {
        db.collection(FireConstants.COLLECTION_LIKES)
            .document(auth.currentUser.uid + thing.id)
            .set(
                hashMapOf(
                    FireConstants.KEY_THING_USER_ID to auth.currentUser.uid,
                    FireConstants.KEY_THING_ID to thing.id
                )
            )
    }
}