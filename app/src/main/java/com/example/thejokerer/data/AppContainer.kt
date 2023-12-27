package com.example.thejokerer.data

import android.content.Context
import com.example.thejokerer.data.database.JokeDb
import com.example.thejokerer.network.JokeApiService
import com.example.thejokerer.network.NetworkConnectionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

interface AppContainer {
    val jokeRepository: JokeRepository
}

// container that takes care of dependencies
class DefaultAppContainer(private val context: Context) : AppContainer {

    private val networkCheck = NetworkConnectionInterceptor(context)
    private val client = OkHttpClient.Builder()
        .addInterceptor(networkCheck)
        .build()




    private val baseUrl = "https://v2.jokeapi.dev/joke/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
//            Json.asConverterFactory("application/json".toMediaType()),
            Json{isLenient = true
            ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType())

        )
        .baseUrl(baseUrl)
        .client(client)
        .build()

    private val retrofitService: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }

    override val jokeRepository: JokeRepository by lazy {
        CachingJokesRepository(JokeDb.getDatabase(context = context).jokeDao(), retrofitService, context)
    }
}