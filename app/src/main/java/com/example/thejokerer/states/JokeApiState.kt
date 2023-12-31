package com.example.thejokerer.states

import com.example.thejokerer.model.Joke

/**
 * This interface represents the state of the API joke retrieval.
 *
 * The `JokeApiState` interface defines four possible states:
 * - [Error]: Indicates an error occurred during the retrieval of API jokes.
 * - [NoInternet]: Indicates that there is no internet connection for fetching API jokes.
 * - [Loading]: Indicates that the process of retrieving API jokes is in progress.
 * - [Success]: Indicates that the retrieval of an API joke was successful, along with the joke data.
 *
 * This sealed interface is used to model the different states that can occur
 * when fetching jokes from an external API in the application.
 */
sealed interface JokeApiState {
    object Error : JokeApiState
    object NoInternet : JokeApiState
    object Loading : JokeApiState
    data class Success(val joke: Joke) : JokeApiState
}
