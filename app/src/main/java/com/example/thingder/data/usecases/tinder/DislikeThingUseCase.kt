package com.example.thingder.data.usecases.tinder

import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.tinder.IDislikeThingUseCase

class DislikeThingUseCase: IDislikeThingUseCase {
    override suspend fun dislike(thing: Thing) {
    }
}