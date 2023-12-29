package com.example.thejokerer.network

import com.example.thejokerer.model.Joke
import kotlinx.serialization.Serializable

@Serializable
data class ApiJoke(
    val joke: String,
)
fun ApiJoke.asDomainObject(): Joke = Joke(joke = joke)








