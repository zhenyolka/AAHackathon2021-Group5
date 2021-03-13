package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.flow.*

class FetchMyThingsUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : IFetchMyThingsUseCase {

    private val stateSharedFlow = MutableStateFlow<List<Thing>>(emptyList())

    private val document = db
        .collection(FireConstants.COLLECTION_THINGS)
        .whereEqualTo(FireConstants.KEY_THING_USER_ID, auth.currentUser.uid)

    override fun fetch(): Flow<List<Thing>> {
        document.addSnapshotListener { value, error ->
            val list = value?.map { it -> it.toDomain() } ?: emptyList()
            stateSharedFlow.value = list
        }
        return stateSharedFlow
    }

    private fun QueryDocumentSnapshot.toDomain(): Thing {
        val id = this[FireConstants.KEY_THING_ID].toString()
        val title = this[FireConstants.KEY_THING_TITLE].toString()
        val imageUrl = this[FireConstants.KEY_THING_IMAGE_ID].toString()
        return Thing(id, title, imageUrl)
    }

}