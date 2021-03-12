package com.example.thingder.model

class Thing (
    var uid: String? = null,
    var name: String? = null,
    var country: String? = null,
    var city: String? = null,
    var title: String? = null,
    var body: String? = null,
    var photo: String? = null,
    val stars: MutableList<String> = mutableListOf()
)