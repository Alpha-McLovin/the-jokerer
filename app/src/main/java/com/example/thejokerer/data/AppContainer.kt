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

/**
 * 
 * This interface represents the application container
 * 
 * @property jokeRepository is the Jokerepository
 * 
 */
interface AppContainer {
    val jokeRepository: JokeRepository
}

/**
 * 
 *  container that takes care of the dependencies, implements the AppContainer
 *
 *  @property context the context
 *  @property jokeRepository an instance of the JokeRepository
 *  @property networkCheck uses the NetworkConnectionInterceptor to check the internet status
 *  @property client the client
 *  @property baseUrl the URL required to use the API
 *  @property retrofit an retrofit instance
 *  @property retrofitService and instance of the RetrofitService
 *  @property jokeDb an instance of the joke database
 * 
 */

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
