package com.example.thingder.fragments.myThings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecase.IFetchMyThings

class MyThingsViewModelFactory(val arg: IFetchMyThings) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IFetchMyThings::class.java)
            .newInstance(arg)
    }
}

