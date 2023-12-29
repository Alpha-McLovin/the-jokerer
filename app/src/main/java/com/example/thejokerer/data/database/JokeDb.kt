package com.example.thejokerer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [DbJoke::class], version = 3)
abstract class JokeDb : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}
