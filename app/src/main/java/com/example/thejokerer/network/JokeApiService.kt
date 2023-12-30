package com.example.thejokerer.network

import retrofit2.http.GET

/**
 * This is the interface of the service responsible for fetching jokes from an external API.
 *
 * The interface defines functions for retrieving jokes of different categories.
 */
interface JokeApiService {

    /**
     * Retrieves a random joke from the API.
     *
     * @return An [ApiJoke] object representing the obtained joke.
     */
    @GET("any?type=single")
    suspend fun getJoke(): ApiJoke

    /**
     * Retrieves a random pun joke from the API.
     *
     * @return An [ApiJoke] object representing the obtained pun joke.
     */
    @GET("Pun?type=single")
    suspend fun getPun(): ApiJoke

    /**
     * Retrieves a random IT-related joke from the API.
     *
     * @return An [ApiJoke] object representing the obtained IT-related joke.
     */
    @GET("Programming?type=single")
    suspend fun getItJoke(): ApiJoke

    /**
     * Retrieves a random miscellaneous joke from the API.
     *
     * @return An [ApiJoke] object representing the obtained miscellaneous joke.
     */
    @GET("Miscellaneous?type=single")
    suspend fun getMiscellaneousJoke(): ApiJoke

    /**
     * Retrieves a random dark humor joke from the API.
     *
     * @return An [ApiJoke] object representing the obtained dark humor joke.
     */
    @GET("Dark?type=single")
    suspend fun getDarkJoke(): ApiJoke
}
