package com.example.thejokerer.states

/**
 * This interface represents the possible states of the favorite jokes retrieval.
 *
 * The `FavoriteState` interface defines three possible states:
 * - [Error]: Indicates an error occurred during the retrieval of favorite jokes.
 * - [Loading]: Indicates that the process of retrieving favorite jokes is in progress.
 * - [Success]: Indicates that the retrieval of favorite jokes was successful.
 *
 * This sealed interface is used to model the different states that can occur
 * when fetching and managing the favorite jokes in the application.
 */
sealed interface FavoriteState {
    object Error : FavoriteState
    object Loading : FavoriteState
    object Success : FavoriteState
}
