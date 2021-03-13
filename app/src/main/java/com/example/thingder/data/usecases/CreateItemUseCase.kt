package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User
import com.example.thingder.domain.usecases.ICreateItemUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CreateItemUseCase(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth
) : ICreateItemUseCase {
    private val document = firestore
        .collection("users")
        .document(auth.currentUser.uid)
        .collection("things")

    override suspend fun createThing(thing: Thing): Boolean {
        document.document(thing.title).set(
            hashMapOf("title" to thing.title)
        )
        return true
    }
}