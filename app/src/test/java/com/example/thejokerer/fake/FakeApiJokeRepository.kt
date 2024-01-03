package com.example.thejokerer.fake

import com.example.thejokerer.data.JokeRepository
import com.example.thejokerer.data.database.asDomainJokes
import com.example.thejokerer.model.Joke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeApiJokeRepository : JokeRepository {
    override fun getFavoriteJokes(): Flow<List<Joke>> {
        val coldFlow = flow {
            emit(FakeDataSource.favoriteJokes.asDomainJokes())
        }
        return coldFlow
    }

    override suspend fun getNewJoke(): Joke {
        return FakeDataSource.joke1
    }

    override suspend fun getNewPun(): Joke {
        return FakeDataSource.joke2
    }

    override suspend fun getNewItJoke(): Joke {
        return FakeDataSource.joke2
    }

    override suspend fun getNewDarkJoke(): Joke {
        return FakeDataSource.joke2
    }

    override suspend fun getNewMiscellaneous(): Joke {
        return FakeDataSource.joke2
    }

    override suspend fun insertFavoriteJoke(joke: Joke) {
        joke.favorite = true
    }

    override suspend fun deleteFavoriteJoke(joke: Joke) {
        joke.favorite = false
    }
}
