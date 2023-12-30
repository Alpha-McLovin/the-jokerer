package com.example.thejokerer.network

import com.example.thejokerer.model.Joke
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing a joke received from the API.
 *
 * @param joke The text of the joke.
 */
@Serializable
data class ApiJoke(
    val joke: String,
)

/**
 * Extension function converting an [ApiJoke] object to a [Joke] domain object.
 *
 * The `asDomainObject` function is an extension function on [ApiJoke] that maps the API-specific joke
 * representation to the domain-specific [Joke] object.
 *
 * @return A [Joke] object representing the converted domain object.
 */
fun ApiJoke.asDomainObject(): Joke = Joke(joke = joke)








