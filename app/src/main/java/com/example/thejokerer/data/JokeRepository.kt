package com.example.thejokerer.data

import android.content.Context
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.thejokerer.data.database.JokeDao
import com.example.thejokerer.data.database.asDbJoke
import com.example.thejokerer.data.database.asDomainJoke
import com.example.thejokerer.data.database.asDomainJokes
import com.example.thejokerer.model.Joke
import com.example.thejokerer.network.ApiJoke
import com.example.thejokerer.network.JokeApiService
import com.example.thejokerer.network.asDomainObject

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException
import java.util.UUID

interface JokeRepository {

    // all items from datasource
    fun getFavoriteJokes(): Flow<List<Joke>>

    // get a new joke from the API
    suspend fun getNewJoke(): Joke

    suspend fun insertFavoriteJoke(joke: Joke)
    suspend fun deleteFavoriteJoke(joke: Joke)

}

class CachingJokesRepository(private val jokeDao: JokeDao, private val jokeApiService: JokeApiService, context: Context) : JokeRepository {

    // this repo contains logic to get the jokes
    override fun getFavoriteJokes(): Flow<List<Joke>> {

        return jokeDao.getAllItems().map {
            it.asDomainJokes()
        }

    }

    override suspend fun getNewJoke() : Joke = jokeApiService.getJoke().asDomainObject()


    override suspend fun insertFavoriteJoke(joke: Joke) {
        jokeDao.delete(joke.asDbJoke())
    }
    override suspend fun deleteFavoriteJoke(joke: Joke) {
        jokeDao.delete(joke.asDbJoke())
    }



}