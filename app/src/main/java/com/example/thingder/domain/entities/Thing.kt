package com.example.thingder.domain.entities

data class Thing(
    val id: String,
    val title: String,
    val imageUrl: String = ""
    //val description: String
)
