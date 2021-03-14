package com.example.thingder.activities.login

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thingder.R
import com.example.thingder.data.usecases.FireConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {
    private var showProgress = MutableLiveData(false)
    val isProgressVisible: LiveData<Boolean> get() = showProgress

    private var _showToast = MutableLiveData<Int>()
    val showToast: LiveData<Int> get() = _showToast

    private var auth: FirebaseAuth = Firebase.auth

    private var isAuth = MutableLiveData(false)

    init {
        isAuth.value = Firebase.auth.currentUser != null
    }

    val isAuthorised: LiveData<Boolean> get() = isAuth

    private fun refreshAuthStatus() {
        isAuth.value = Firebase.auth.currentUser != null
    }


    fun signIn(email: String, password: String, activity: Activity) {
        showProgress.value = true

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                Log.d("SIGNIN_TAG", "signIn:onComplete:" + task.isSuccessful)
                showProgress.value = false

                if (task.isSuccessful) {
                    _showToast.value = R.string.sign_in_success
                    refreshAuthStatus()
                } else {
                    _showToast.value = R.string.sign_in_failed
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
                    _showToast.value = R.string.register_success
                    refreshAuthStatus()
                    createUserToFirebase()
                } else {
                    _showToast.value = R.string.register_failed
                }
            }
    }

    private fun createUserToFirebase() {
        FirebaseFirestore.getInstance().collection(FireConstants.COLLECTION_USERS)
            .document(auth.currentUser.uid)
            .set(
                hashMapOf(
                    FireConstants.KEY_USER_ID to auth.currentUser.uid,
                    FireConstants.KEY_USER_EMAIL to auth.currentUser.email,
                )
            )
    }
}