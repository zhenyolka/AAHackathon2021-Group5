package com.example.thingder.domain.usecases

import android.net.Uri

interface ICreateItemUseCase {
    suspend fun createThing(title: String, description: String, uri: Uri): Boolean
}