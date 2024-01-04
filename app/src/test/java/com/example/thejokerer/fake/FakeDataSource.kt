package com.example.thejokerer.fake

import com.example.thejokerer.data.database.DbJoke
import com.example.thejokerer.model.Joke

/**
 * A fake data source providing mock data for testing purposes.
 *
 * This object contains fake instances of [Joke] and [DbJoke] to be used in testing scenarios.
 * It is used to simulate interactions with real data sources such as the databases and the API.
 */
object FakeDataSource {

    val joke1 = Joke("using fake jokes is funny", favorite = false)
    val joke2 = Joke("these tests must be the joke", favorite = false)

    val favoriteJokes = listOf(DbJoke(joke1.joke), DbJoke(joke2.joke))
}
