package com.example.thingder.domain.usecase

import com.example.thingder.domain.entities.Thing
import kotlinx.coroutines.flow.Flow

interface IFetchMyThings {
    fun fetch(): Flow<List<Thing>>
}