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
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ICreateItemUseCase {
    private val document = firestore
        .collection("users")
        .document(auth.currentUser.uid)
        .collection("things")

    override suspend fun createThing(thing: Thing): Boolean {
        val userId = Firebase.auth.currentUser!!.uid
//        val restaurantRef = firestore
//            .collection("users")
//            .document(userId)
//            .collection("things")
//            .document(thing.title)
//            .set(thing.toData())

        document.document(thing.title).set(
            hashMapOf("title" to thing.title)
        )

//        val usersRef = firestore.collection("users").document(userId)
//        val thingsRef = usersRef.collection("things").document()
//        firestore.runTransaction { transaction ->
//            val user = transaction.get(usersRef).toObject<User>()
//            if (user == null) {
//                throw Exception("Resraurant not found at ${usersRef.path}")
//            }
//
//            transaction.set(usersRef, user)
//            transaction.set(thingsRef, thing)
//
//            null
//        }
        return true
    }


}