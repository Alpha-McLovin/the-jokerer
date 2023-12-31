package com.example.thejokerer

import android.app.Application
import com.example.thejokerer.data.AppContainer
import com.example.thejokerer.data.DefaultAppContainer

/**
 * Custom application class for the Joke Application.
 *
 * The `JokeApplication` class extends the Android [Application] class and serves as the entry point
 * for the Joke Application. It initializes the application-wide dependencies using the [AppContainer].
 *
 * @property container The application-wide dependency container providing access to various components.
 */
class JokeApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
