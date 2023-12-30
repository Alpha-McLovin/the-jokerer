package com.example.thejokerer.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 *
 * This interface is a DAO for managing jokes in the database
 * and defines methods for inserting, deleting, and querying jokes.
 *
 */
@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbJoke)

    @Delete
    suspend fun delete(item: DbJoke)

    @Query("SELECT * from jokes")
    fun getAllItems(): Flow<List<DbJoke>>
}
