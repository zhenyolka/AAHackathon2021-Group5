package com.example.thingder.fragments.myThings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase

class MyThingsViewModelFactory(val arg: IFetchMyThingsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IFetchMyThingsUseCase::class.java)
            .newInstance(arg)
    }
}

