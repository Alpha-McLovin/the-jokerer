package com.example.thejokerer

import android.app.Application
import com.example.thejokerer.data.AppContainer
import com.example.thejokerer.data.DefaultAppContainer

class JokeApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
