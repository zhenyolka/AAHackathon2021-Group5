package com.example.thingder.data.usecases

import android.os.Parcel
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
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
            value?.map { it -> it.toDomain() }
            stateSharedFlow.value = list
        }
        return stateSharedFlow
    }

    private fun QueryDocumentSnapshot.toDomain(): Thing {
        //TODO: return id
        //val id = this["id"] as Int
        val title = this["title"].toString()
        return Thing(3, title)
    }

}