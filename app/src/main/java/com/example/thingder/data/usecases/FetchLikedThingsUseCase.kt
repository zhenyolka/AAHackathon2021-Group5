package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchLikedThingsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FetchLikedThingsUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
): IFetchLikedThingsUseCase {

    private val stateSharedFlow = MutableStateFlow<List<Thing>>(emptyList())

    private val document = db
        .collection(FireConstants.COLLECTION_THINGS)
        .whereEqualTo(FireConstants.KEY_USER_ID, auth.currentUser.uid)

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
        return Thing(id, title)
    }

//    override fun fetch(): Flow<List<Thing>> {
//
////        return flow {
////            emit(
////                listOf(
////                    Thing(
////                        id = "1",
////                        title = "Brick"
////                    ),
////                    Thing(
////                        id = "2012",
////                        title = "End of The World Button"
////                    ),
////                    Thing(
////                        id = "42",
////                        title = "Universe"
////                    )
////                )
////            )
////        }
//    }
}