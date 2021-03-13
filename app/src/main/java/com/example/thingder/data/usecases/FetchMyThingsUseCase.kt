package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.*

class FetchMyThingsUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
): IFetchMyThingsUseCase {

    private val document = db
        .collection("users")
        .document(auth.currentUser.uid)
        .collection("things")

    private val stateSharedFlow = MutableStateFlow<List<Thing>>(emptyList())

    override fun fetch(): Flow<List<Thing>> {

        document.addSnapshotListener { value, error ->
            //TODO: map into list of things
            val list = mutableListOf<Thing>()
            value?.forEach {
                val id = it["id"] as Int
                val title = it["title"].toString()
                list.add(Thing(id, title))
            }
            stateSharedFlow.value = list
        }
        return stateSharedFlow
//        return flow {
//            emit(
//                listOf(
//                    Thing(
//                        id = 1,
//                        title = "Brick"
//                    ),
//                    Thing(
//                        id = 2012,
//                        title = "End of The World Button"
//                    ),
//                    Thing(
//                        id = 42,
//                        title = "Universe"
//                    )
//                )
//            )
//
//        }
    }
}