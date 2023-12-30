package com.example.thejokerer.data

import android.content.Context
import com.example.thejokerer.data.database.JokeDao
import com.example.thejokerer.data.database.asDbJoke
import com.example.thejokerer.data.database.asDomainJokes
import com.example.thejokerer.model.Joke
import com.example.thejokerer.network.JokeApiService
import com.example.thejokerer.network.asDomainObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository interface for managing jokes in the application.
 *
 * The `JokeRepository` is responsible for handling interactions with joke-related data,
 * including fetching favorite jokes, retrieving new jokes from various categories,
 * and managing the favorites list.
 */
interface JokeRepository {

    /**
     * Retrieves a list of favorite jokes.
     *
     * This function returns a [Flow] that emits a list of [Joke] objects representing favorite jokes.
     *
     * @return A [Flow] of a list of favorite [Joke] objects.
     */
    fun getFavoriteJokes(): Flow<List<Joke>>

    /**
     * Retrieves a random joke from the API regardless of the category.
     *
     * @return The new [Joke] obtained from the API.
     */
    suspend fun getNewJoke(): Joke

    /**
     * Retrieves a new pun joke from the API.
     *
     * @return The new [Joke] of the pun category obtained from the API.
     */
    suspend fun getNewPun(): Joke

    /**
     * Retrieves a new IT-related joke from the API.
     *
     * @return The new [Joke] of the IT category obtained from the API.
     */
    suspend fun getNewItJoke(): Joke

    /**
     * Retrieves a new dark humor joke from the API.
     *
     * @return The new [Joke] of the dark humor category obtained from the API.
     */
    suspend fun getNewDarkJoke(): Joke

    /**
     * Retrieves a new miscellaneous joke from the API.
     *
     * @return The new [Joke] of the miscellaneous category obtained from the API.
     */
    suspend fun getNewMiscellaneous(): Joke

    /**
     * Inserts a joke into the list of favorite jokes.
     *
     * @param joke The [Joke] to be added to the list of favorites.
     */
    suspend fun insertFavoriteJoke(joke: Joke)

    /**
     * Deletes a joke from the list of favorite jokes.
     *
     * @param joke The [Joke] to be removed from the list of favorites.
     */
    suspend fun deleteFavoriteJoke(joke: Joke)
}

/**
 * Implementation of [JokeRepository] that provides caching for joke data.
 *
 * The `CachingJokesRepository` class combines data obtained from a local database (via [JokeDao])
 * and a remote API service (via [JokeApiService]) to provide a caching mechanism for managing jokes.
 * It handles the retrieval of favorite jokes, as well as fetching new jokes from various categories
 * and managing the list of favorite jokes.
 *
 * @param jokeDao The [JokeDao] responsible for database interactions.
 * @param jokeApiService The [JokeApiService] responsible for making API calls to fetch jokes.
 * @param context The [Context] used for accessing application-specific resources.
 */
class CachingJokesRepository(private val jokeDao: JokeDao, private val jokeApiService: JokeApiService, context: Context) : JokeRepository {

    // this repo contains logic to get the jokes
    override fun getFavoriteJokes(): Flow<List<Joke>> {
        return jokeDao.getAllItems().map {
            it.asDomainJokes()
        }
    }

    override suspend fun getNewJoke(): Joke = jokeApiService.getJoke().asDomainObject()
    override suspend fun getNewPun(): Joke = jokeApiService.getPun().asDomainObject()

    override suspend fun getNewItJoke(): Joke = jokeApiService.getItJoke().asDomainObject()

    override suspend fun getNewDarkJoke(): Joke = jokeApiService.getDarkJoke().asDomainObject()

    override suspend fun getNewMiscellaneous(): Joke = jokeApiService.getMiscellaneousJoke().asDomainObject()

    override suspend fun insertFavoriteJoke(joke: Joke) {
        jokeDao.insert(joke.asDbJoke())
    }
    override suspend fun deleteFavoriteJoke(joke: Joke) {
        jokeDao.delete(joke.asDbJoke())
    }
}
