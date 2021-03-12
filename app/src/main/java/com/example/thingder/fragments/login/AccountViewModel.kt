package com.example.thingder.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountViewModel : ViewModel() {

    private var showProgress = MutableLiveData(false)
    val isProgressVisible: LiveData<Boolean> get() = showProgress

    fun showProgressBar(b: Boolean){showProgress.value = b}

    private var isAuth = MutableLiveData(false)
    init {
        isAuth.value = Firebase.auth.currentUser != null
    }
    val isAuthorised: LiveData<Boolean> get() = isAuth

    fun refreshAuthStatus(){isAuth.value = Firebase.auth.currentUser != null}
}
