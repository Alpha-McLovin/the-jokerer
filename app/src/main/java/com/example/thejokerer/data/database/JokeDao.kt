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

    /**
     * Inserts a joke into the `jokes` table or replaces it if it already exists.
     *
     * @param item The joke to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbJoke)

    /**
     * Deletes a joke from the `jokes` table.
     *
     * @param item The joke to be deleted.
     */
    @Delete
    suspend fun delete(item: DbJoke)

    /**
     * Retrieves all jokes from the `jokes` table as a flow of lists
     *
     * @return A [Flow] emitting lists of [DbJoke] items.
     */
    @Query("SELECT * from jokes")
    fun getAllItems(): Flow<List<DbJoke>>
}
