package com.example.thingder.activities.login

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    private var showProgress = MutableLiveData(false)
    val isProgressVisible: LiveData<Boolean> get() = showProgress

    private var _showToast = MutableLiveData<String>()
    val showToast: LiveData<String> get() = _showToast

    private var auth: FirebaseAuth = Firebase.auth

    private var isAuth = MutableLiveData(false)
    init {
        isAuth.value = Firebase.auth.currentUser != null
    }
    val isAuthorised: LiveData<Boolean> get() = isAuth

    private fun refreshAuthStatus(){isAuth.value = Firebase.auth.currentUser != null}


    fun signIn(email: String, password: String, activity: Activity) {
        showProgress.value = true

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                Log.d("SIGNIN_TAG", "signIn:onComplete:" + task.isSuccessful)
                showProgress.value = false

                if (task.isSuccessful) {
                    _showToast.value = "Sign In Success"
                    refreshAuthStatus()
                } else {
                    _showToast.value = "Sign In Failed"
                }
            }
    }

    fun register(email: String, password: String, activity: Activity) {
        showProgress.value = true

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                Log.d("REGISTER_TAG", "createUser:onComplete:" + task.isSuccessful)
                showProgress.value = false

                if (task.isSuccessful) {
                    _showToast.value = "Register Success"
                    refreshAuthStatus()
                } else {
                    _showToast.value = "Register Failed"
                }
            }
    }

    fun logout() {
        Firebase.auth.signOut()
        refreshAuthStatus()
    }
}