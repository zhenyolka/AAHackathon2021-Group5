package com.example.thingder.domain.usecases.tinder

import com.example.thingder.domain.entities.Thing
import kotlinx.coroutines.flow.Flow

interface IFetchNearbyThingUseCase {
    fun fetch(): Flow<List<Thing>>
}