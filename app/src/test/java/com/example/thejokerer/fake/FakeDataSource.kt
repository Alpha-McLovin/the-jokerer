package com.example.thejokerer.fake

import com.example.thejokerer.data.database.DbJoke
import com.example.thejokerer.model.Joke

object FakeDataSource {

    val joke1 = Joke("using fake jokes is funny", favorite = false)
    val joke2 = Joke("these tests must be the joke", favorite = false)

    val favoriteJokes = listOf(DbJoke(joke1.joke), DbJoke(joke2.joke))
}
