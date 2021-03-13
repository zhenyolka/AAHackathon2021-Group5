package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchLikedThingsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FetchLikedThingsUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : IFetchLikedThingsUseCase {

    private val stateSharedFlow = MutableStateFlow<List<Thing>>(emptyList())

    private val document = db
        .collection(FireConstants.COLLECTION_LIKES)
        .whereEqualTo(FireConstants.KEY_THING_USER_ID, auth.currentUser.uid)

    override fun fetch(): Flow<List<Thing>> {
        document.addSnapshotListener { value, error ->
            val thingIds = value?.getListOfThingIds() ?: emptyList()

            db.collection(FireConstants.COLLECTION_THINGS)
                .addSnapshotListener { newValue, _ ->
                    stateSharedFlow.value = newValue.getListOfThings(thingIds)
                }

        }
        return stateSharedFlow
    }

    private fun QuerySnapshot.getListOfThingIds(): List<String> {
        return map {
            it[FireConstants.KEY_THING_ID].toString()
        }
    }

    private fun QuerySnapshot?.getListOfThings(thingIds: List<String>): List<Thing> {
        val list = mutableListOf<Thing>()
        this?.forEach { snapshot ->

            if (thingIds.contains(snapshot.id)) {
                list.add(snapshot.toDomain())
            }
        }

        return list
    }

    private fun QueryDocumentSnapshot.toDomain(): Thing {
        val id = this[FireConstants.KEY_THING_ID].toString()
        val title = this[FireConstants.KEY_THING_TITLE].toString()
        return Thing(id, title)
    }

}