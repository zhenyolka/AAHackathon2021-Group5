package com.example.thingder.fragments.myThingsLikedByOthers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase

class MyThingsLikedByOthersViewModel(private val fetchItemsUseCase: IFetchMyThingsUseCase): ViewModel() {
    //private val _items = MutableLiveData<List<Pair<User, Thing>>>()
    val items: LiveData<List<Pair<User, Thing>>> = fetchItemsUseCase.fetch().asLiveData()

}