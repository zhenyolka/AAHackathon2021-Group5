package com.example.thingder.data.usecases

import com.example.thingder.domain.entities.Thing
import com.google.firebase.firestore.QueryDocumentSnapshot


fun QueryDocumentSnapshot.toDomain(): Thing {
    val id = this.id
    val title = this[FireConstants.KEY_THING_TITLE].toString().capitalize()
    val description = this[FireConstants.KEY_THING_DESCRIPTION].toString().capitalize()
    val imageUrl = this[FireConstants.KEY_THING_IMAGE_ID].toString()
    return Thing(
        id =id,
        title = title,
        description = description,
        imageUrl = imageUrl
    )
}
