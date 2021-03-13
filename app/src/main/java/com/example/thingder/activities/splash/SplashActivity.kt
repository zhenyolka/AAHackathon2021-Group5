package com.example.thingder.activities.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.thingder.MainActivity
import com.example.thingder.R
import com.example.thingder.activities.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.splashScreenTheme)
        super.onCreate(savedInstanceState)
        val intent = if (viewModel.authorized) {
            Intent(this, MainActivity::class.java)
        } else { Intent(this, LoginActivity::class.java)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}