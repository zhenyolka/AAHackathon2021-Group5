package com.example.thingder.domain.usecases

import com.example.thingder.domain.entities.Thing

interface ICreateItemUseCase {
    suspend fun createThing(thing: Thing){

    }
}