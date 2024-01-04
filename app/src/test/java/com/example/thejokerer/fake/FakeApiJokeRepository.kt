package com.example.thejokerer.fake

import com.example.thejokerer.data.JokeRepository
import com.example.thejokerer.data.database.asDomainJokes
import com.example.thejokerer.model.Joke
import com.example.thejokerer.network.asDomainObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A fake implementation of [JokeRepository] for testing purposes.
 *
 * This repository provides mock implementations of the methods defined in [JokeRepository] interface.
 * It is designed to simulate interactions with a real repository, allowing for unit testing without
 * relying on actual network or database operations.
 *
 * @param apiService A fake implementation of [JokeApiService] used to simulate API responses.
 */
class FakeApiJokeRepository(private val apiService: FakeJokeApiService) : JokeRepository {

    /**
     * Retrieves a flow of fake favorite jokes for testing purposes.
     *
     * This method emits a flow of mock favorite jokes stored in [FakeDataSource].
     *
     * @return A [Flow] emitting a list of fake favorite jokes.
     */
    override fun getFavoriteJokes(): Flow<List<Joke>> {
        val coldFlow = flow {
            emit(FakeDataSource.favoriteJokes.asDomainJokes())
        }
        return coldFlow
    }

    /**
     * Retrieves a fake new joke from the fake API service.
     *
     * This method simulates the retrieval of a new joke from a remote API.
     *
     * @return A fake new joke.
     */
    override suspend fun getNewJoke(): Joke {
        return apiService.getJoke().asDomainObject()
    }

    /**
     * Retrieves a new fake pun joke from the fake API service.
     *
     * @return A new fake pun joke.
     */
    override suspend fun getNewPun(): Joke {
        return apiService.getPun().asDomainObject()
    }

    /**
     * Retrieves a new fake IT-related joke from the fake API service.
     *
     * @return A new fake IT-related joke.
     */
    override suspend fun getNewItJoke(): Joke {
        return apiService.getItJoke().asDomainObject()
    }

    /**
     * Retrieves a new fake dark joke from the fake API service.
     *
     * @return A new fake dark joke.
     */
    override suspend fun getNewDarkJoke(): Joke {
        return apiService.getDarkJoke().asDomainObject()
    }

    /**
     * Retrieves a new fake miscellaneous joke from the fake API service.
     *
     * @return A new fake miscellaneous joke.
     */
    override suspend fun getNewMiscellaneous(): Joke {
        return apiService.getMiscellaneousJoke().asDomainObject()
    }

    /**
     * Simulates inserting a joke into the list of favorites.
     *
     * This method sets the `favorite` property of the provided joke to `true`.
     *
     * @param joke The joke to be marked as a favorite.
     */
    override suspend fun insertFavoriteJoke(joke: Joke) {
        joke.favorite = true
    }

    /**
     * Simulates removing a joke from the list of favorites.
     *
     * This method sets the `favorite` property of the provided joke to `false`.
     *
     * @param joke The joke to be removed from favorites.
     */
    override suspend fun deleteFavoriteJoke(joke: Joke) {
        joke.favorite = false
    }
}
