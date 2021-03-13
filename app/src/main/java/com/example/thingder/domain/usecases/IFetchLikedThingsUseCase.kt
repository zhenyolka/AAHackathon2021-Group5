package com.example.thingder.domain.usecases

import com.example.thingder.domain.entities.Thing
import kotlinx.coroutines.flow.Flow

interface IFetchLikedThingsUseCase {
    fun fetch(): Flow<List<Thing>>

}