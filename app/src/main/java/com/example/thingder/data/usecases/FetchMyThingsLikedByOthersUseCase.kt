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
                    val thingsList = newValue.getListOfThings(thingIds)

                    db.collection(FireConstants.COLLECTION_USERS)
                        .addSnapshotListener { userValue, _ ->
                            val mutList = mutableListOf<Pair<User, Thing>>()
                            userValue?.forEach {
                                val userId = it[FireConstants.KEY_USER_ID].toString()
                                val userEmail = it[FireConstants.KEY_USER_EMAIL].toString()

                                val pair = thingsList.find { it.first.email == userId }

                                if (pair != null) {
                                    mutList.add(User(userEmail) to pair.second)
                                }
                            }
                            stateSharedFlow.value = mutList

                        }
                }

        }
        return stateSharedFlow
    }

    private fun QuerySnapshot.getListOfThingIds(): List<Pair<String, String>> {
        return map {
            it[FireConstants.KEY_THING_ID].toString() to
                    it[FireConstants.KEY_THING_USER_ID].toString()
        }
    }


    private fun QuerySnapshot?.getListOfThings(thingIds: List<Pair<String, String>>): List<Pair<User, Thing>> {
        val list = mutableListOf<Pair<User, Thing>>()
        this?.forEach { snapshot ->

            val pair = thingIds.find { it.first == snapshot.id }
            if (pair != null) {
                list.add(User(pair.second) to snapshot.toDomain())
            }
        }

        return list
    }


}