package com.example.thejokerer.states

import com.example.thejokerer.model.Joke

sealed interface JokeApiState {
    object Error : JokeApiState
    object NoInternet : JokeApiState
    object Loading : JokeApiState
    data class Success(val joke: Joke) : JokeApiState
}
