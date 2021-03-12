package com.example.thingder

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injection.setup(this)
    }
}