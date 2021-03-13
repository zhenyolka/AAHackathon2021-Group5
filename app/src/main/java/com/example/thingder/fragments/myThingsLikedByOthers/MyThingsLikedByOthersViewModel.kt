package com.example.thingder.fragments.myThingsLikedByOthers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User

class MyThingsLikedByOthersViewModel: ViewModel() {
    private val _items = MutableLiveData<List<Pair<User, Thing>>>()
    val items: LiveData<List<Pair<User, Thing>>> get() = _items

    init {
        // TODO: replace with API call
        _items.postValue((listOf(
            Pair(User(1, "prock@mail.ru"), Thing(1, "A nice hat")),
            Pair(User(2, "newuser@mail.ru"), Thing(2, "Old pair of skies")),
            Pair(User(3, "vfock@ya.ru"), Thing(1, "A nice hat")),
            Pair(User(3, "vfock@ya.ru"), Thing(3, "Big umbrella")),
            Pair(User(4, "newsru@news.ru"), Thing(4, "A nice hat")),
        )))
    }

}