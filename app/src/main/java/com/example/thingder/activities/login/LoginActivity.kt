package com.example.thingder.activities.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.thingder.MainActivity
import com.example.thingder.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonFakeLogin.setOnClickListener {
            navigateToMainActivity()
        }

        loginViewModel.showToast.observe(this, { msg ->
            Log.d("SIGNIN_TAG", msg)
        })

        loginViewModel.isProgressVisible.observe(this, { showProgress ->
            presentLoading(showProgress)
        })

        loginViewModel.isAuthorised.observe(this, { isAuthorised ->
            Log.d("SIGNIN_TAG", "isAuthorised: $isAuthorised")
            if (isAuthorised) {
                binding.tvUser.text = Firebase.auth.currentUser?.email.toString()
                navigateToMainActivity()
            } else {
                binding.tvUser.text = "You are not authorised"
            }
        })

        with(binding) {
            buttonSignIn.setOnClickListener {
                signIn()
            }
            buttonRegister.setOnClickListener {
                register()
            }
            buttonLogOut.setOnClickListener {
                loginViewModel.logout()
            }
        }
    }

    private fun signIn() {
        Log.d("SIGNIN_TAG", "signIn")
        if (!validateForm()) {
            return
        }
        val email = binding.fieldEmail.text.toString()
        val password = binding.fieldPassword.text.toString()

        loginViewModel.signIn(email, password, this)
    }

    private fun register() {
        if (!validateForm()) {
            return
        }
        val email = binding.fieldEmail.text.toString()
        val password = binding.fieldPassword.text.toString()

        loginViewModel.register(email, password, this)
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun validateForm(): Boolean {
        var result = true
        if (TextUtils.isEmpty(binding.fieldEmail.text.toString())) {
            binding.fieldEmail.error = "Required"
            result = false
        } else {
            binding.fieldEmail.error = null
        }

        if (TextUtils.isEmpty(binding.fieldPassword.text.toString())) {
            binding.fieldPassword.error = "Required"
            result = false
        } else {
            binding.fieldPassword.error = null
        }

        return result
    }

    private fun presentLoading(b: Boolean) {
        binding.progressBar.visibility =
            if (b) View.VISIBLE
            else View.INVISIBLE
    }
}