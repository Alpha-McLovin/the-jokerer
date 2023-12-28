package com.example.thejokerer.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thejokerer.model.Joke

@Entity(tableName = "jokes")
data class DbJoke(
    @PrimaryKey
    val joke: String = "",
    val favorite: Boolean = false,

    )


fun DbJoke.asDomainJoke(): Joke {
    return Joke(
        this.joke,
        this.favorite,
    )
}

fun Joke.asDbJoke(): DbJoke {
    return DbJoke(
        joke = this.joke,
        favorite = this.favorite,
    )
}

fun List<DbJoke>.asDomainJokes(): List<Joke> {
    var jokeList = this.map {
        Joke(it.joke, it.favorite)
    }
    return jokeList
}