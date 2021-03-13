package com.example.thingder.domain.entities

data class Thing (
    val id: Int,
    val title: String,
    var photoUri: String = "",
    //val description: String
)