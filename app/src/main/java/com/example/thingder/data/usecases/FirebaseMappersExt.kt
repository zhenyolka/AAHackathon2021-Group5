package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.google.firebase.firestore.QueryDocumentSnapshot


fun QueryDocumentSnapshot.toDomain(): Thing {
    val id = this.id
    val title = this[FireConstants.KEY_THING_TITLE].toString()
    val imageUrl = this[FireConstants.KEY_THING_IMAGE_ID].toString()
    return Thing(id, title, imageUrl)
}
