package com.example.thejokerer.data

import android.content.Context
import androidx.room.Room
import com.example.thejokerer.data.database.JokeDb
import com.example.thejokerer.network.JokeApiService
import com.example.thejokerer.network.NetworkConnectionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val jokeRepository: JokeRepository
}

// container that takes care of dependencies
class DefaultAppContainer(private val context: Context) : AppContainer {

    override val jokeRepository: JokeRepository by lazy {
        CachingJokesRepository(jokeDb.jokeDao(), retrofitService, context)
    }

    private val networkCheck = NetworkConnectionInterceptor(context)
    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()


    private val baseUrl = "https://v2.jokeapi.dev/joke/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
//            Json.asConverterFactory("application/json".toMediaType()),
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
                .asConverterFactory("application/json".toMediaType()),

        )
        .baseUrl(baseUrl)
        .client(client)
        .build()

    private val retrofitService: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }

    private val jokeDb: JokeDb by lazy {
        Room.databaseBuilder(
            context,
            JokeDb::class.java,
            "joke_database",
        ).fallbackToDestructiveMigration()
            .build()
    }
}
