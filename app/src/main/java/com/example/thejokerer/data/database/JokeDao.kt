package com.example.thejokerer.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbJoke)


    @Delete
    suspend fun delete(item: dbJoke)

    @Query("SELECT * from jokes WHERE joke = :joke")
    fun getItem(name: String): Flow<dbJoke>

    @Query("SELECT * from jokes ORDER BY joke ASC")
    fun getAllItems(): Flow<List<dbJoke>>
}