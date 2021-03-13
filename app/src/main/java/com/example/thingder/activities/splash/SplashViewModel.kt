package com.example.thingder.activities.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel: ViewModel() {
    val authorized: Boolean = Firebase.auth.currentUser != null
}