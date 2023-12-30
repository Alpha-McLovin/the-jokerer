package com.example.thejokerer.model

/**
 * Data class representing a joke.
 *
 * The `Joke` class encapsulates information about a joke, including the actual joke text
 * and whether it is marked as a favorite.
 *
 * @property joke The text of the joke.
 * @property favorite Indicates whether the joke is marked as a favorite (default is `false`).
 */
data class Joke(
    var joke: String,
    var favorite: Boolean = false,
)
