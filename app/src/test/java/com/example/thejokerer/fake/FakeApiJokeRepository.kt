package com.example.thejokerer.fake

import com.example.thejokerer.data.JokeRepository
import com.example.thejokerer.data.database.asDomainJokes
import com.example.thejokerer.model.Joke
import com.example.thejokerer.network.asDomainObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeApiJokeRepository(private val apiService: FakeJokeApiService) : JokeRepository {
    override fun getFavoriteJokes(): Flow<List<Joke>> {
        val coldFlow = flow {
            emit(FakeDataSource.favoriteJokes.asDomainJokes())
        }
        return coldFlow
    }

    override suspend fun getNewJoke(): Joke {
        return apiService.getJoke().asDomainObject()
    }

    override suspend fun getNewPun(): Joke {
        return apiService.getPun().asDomainObject()
    }

    override suspend fun getNewItJoke(): Joke {
        return apiService.getItJoke().asDomainObject()
    }

    override suspend fun getNewDarkJoke(): Joke {
        return apiService.getDarkJoke().asDomainObject()
    }

    override suspend fun getNewMiscellaneous(): Joke {
        return apiService.getMiscellaneousJoke().asDomainObject()
    }

    override suspend fun insertFavoriteJoke(joke: Joke) {
        joke.favorite = true
    }

    override suspend fun deleteFavoriteJoke(joke: Joke) {
        joke.favorite = false
    }
}
