package com.example.thejokerer.fake

import com.example.thejokerer.network.ApiJoke
import com.example.thejokerer.network.JokeApiService

/**
 * A fake implementation of the [JokeApiService] for testing purposes.
 *
 * This class provides mock responses for various joke types, allowing
 * the application to simulate interactions with a real API service.
 */
class FakeJokeApiService : JokeApiService {

    /**
     * Simulates the API call to get a random joke.
     *
     * @return An [ApiJoke] containing a fake random joke.
     */
    override suspend fun getJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke1.joke)
    }


    /**
     * Simulates the API call to get a pun.
     *
     * @return An [ApiJoke] containing a fake pun.
     */
    override suspend fun getPun(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }

    /**
     * Simulates the API call to get an IT-related joke.
     *
     * @return An [ApiJoke] containing a fake IT-related joke.
     */
    override suspend fun getItJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }

    /**
     * Simulates the API call to get a miscellaneous joke.
     *
     * @return An [ApiJoke] containing a fake miscellaneous joke.
     */
    override suspend fun getMiscellaneousJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }

    /**
     * Simulates the API call to get a dark humor joke.
     *
     * @return An [ApiJoke] containing a fake dark humor joke.
     */
    override suspend fun getDarkJoke(): ApiJoke {
        return ApiJoke(FakeDataSource.joke2.joke)
    }
}
