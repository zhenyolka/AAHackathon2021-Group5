package com.example.thingder.domain.usecases.tinder

import com.example.thingder.domain.entities.Thing

interface ILikeThingUseCase {
    suspend fun like(thing: Thing)
}