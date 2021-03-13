package com.example.thingder.domain.usecases.tinder

import com.example.thingder.domain.entities.Thing

interface IDislikeThingUseCase {
    suspend fun dislike(thing : Thing)
}