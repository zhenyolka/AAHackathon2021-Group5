package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User
import com.example.thingder.domain.usecases.IFetchMyThingsLikedByOthersUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FetchMyThingsLikedByOthersUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : IFetchMyThingsLikedByOthersUseCase {


    private val stateSharedFlow = MutableStateFlow<List<Pair<User, Thing>>>(emptyList())

    private val document = db
        .collection(FireConstants.COLLECTION_LIKES)
        .whereNotEqualTo(FireConstants.KEY_THING_USER_ID, auth.currentUser.uid)

    override fun fetch(): Flow<List<Pair<User, Thing>>> {

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


    private fun QuerySnapshot?.getListOfThings(thingIds: List<String>): List<Pair<User, Thing>> {
        val list = mutableListOf<Pair<User, Thing>>()
        this?.forEach { snapshot ->

            if (thingIds.contains(snapshot.id)) {
                list.add(User(23, "Kek") to snapshot.toDomain())
            }
        }

        return list
    }


}