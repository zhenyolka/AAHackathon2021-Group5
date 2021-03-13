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
            val list = mutableListOf<Thing>()
            value?.forEach {
                //TODO: return id
                //val id = it["id"] as Int
                val title = it["title"].toString()
                list.add(Thing(3, title))
            }
            stateSharedFlow.value = list
        }
        return stateSharedFlow
    }

}