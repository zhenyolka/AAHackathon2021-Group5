package com.example.thingder.domain.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface IFetchMyThingsLikedByOthersUseCase {
    fun fetch() : Flow<List<Pair<User, Thing>>>
}