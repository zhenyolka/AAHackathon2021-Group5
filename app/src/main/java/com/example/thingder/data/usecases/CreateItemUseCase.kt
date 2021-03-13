package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.ICreateItemUseCase

class CreateItemUseCase: ICreateItemUseCase {
    override suspend fun createThing(thing: Thing): Boolean {
        return thing.title.isNotEmpty()
    }
}