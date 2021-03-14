package com.example.thingder.domain.entities

data class Thing(
    val id: String,
    val title: String,
    val description: String = "",
    val imageUrl: String
)
