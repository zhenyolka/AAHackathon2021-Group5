package com.example.thingder.data.usecases.tinder

import com.example.thingder.data.usecases.FireConstants
import com.example.thingder.data.usecases.toDomain
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.tinder.IFetchNearbyThingUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FetchNearbyThingUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : IFetchNearbyThingUseCase {

    private val stateSharedFlow = MutableStateFlow<List<Thing>>(emptyList())

    private val document = db
        .collection(FireConstants.COLLECTION_THINGS)
        .whereNotEqualTo(FireConstants.KEY_THING_USER_ID, auth.currentUser.uid)

    override fun fetch(): Flow<List<Thing>> {
        document.addSnapshotListener { value, error ->
            val list = value?.map { it -> it.toDomain() } ?: emptyList()
            stateSharedFlow.value = list
        }
        return stateSharedFlow
    }

}