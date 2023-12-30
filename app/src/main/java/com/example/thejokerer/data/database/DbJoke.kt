package com.example.thejokerer.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thejokerer.model.Joke

/**
 * This Entity is the joke format that is persisted in the database
 *
 * @property joke is the joke itself in string
 * @property favorite is the boolean that defines whether the joke is favorite or not
 */
@Entity(tableName = "jokes")
data class DbJoke(
    @PrimaryKey
    val joke: String = "",
    val favorite: Boolean = false,
)

/**
 * This helper function converts a Joke to a DbJoke
 *
 * @return DbJoke
 */
fun Joke.asDbJoke(): DbJoke {
    return DbJoke(
        joke = this.joke,
        favorite = this.favorite,
    )
}

/**
 *
 * This helper function converts each joke from a list of DbJokes to a list of domain jokes
 *
 * @return list of domain jokes
 *
 */
fun List<DbJoke>.asDomainJokes(): List<Joke> {
    var jokeList = this.map {
        Joke(it.joke, it.favorite)
    }
    return jokeList
}
