package com.example.thingder.fragments.myThings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecase.FetchMyThings

class MyThingsViewModelFactory(val arg: FetchMyThings) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FetchMyThings::class.java)
            .newInstance(arg)
    }
}

