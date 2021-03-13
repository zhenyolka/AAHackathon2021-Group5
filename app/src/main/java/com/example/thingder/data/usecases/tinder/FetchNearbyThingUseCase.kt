package com.example.thingder.data.usecases.tinder

import com.example.thingder.data.usecases.FetchMyThingsUseCase
import com.example.thingder.data.usecases.FireConstants
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.tinder.IFetchNearbyThingUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

//TODO CHANGE LOGIC
class FetchNearbyThingUseCase(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : IFetchNearbyThingUseCase {
    private val useCase = FetchMyThingsUseCase(db, auth)
    override fun fetch() = useCase.fetch()
}