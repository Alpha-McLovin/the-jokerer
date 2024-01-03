package com.example.thejokerer

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.thejokerer.data.database.JokeDao
import com.example.thejokerer.data.database.JokeDb
import com.example.thejokerer.data.database.asDbJoke
import com.example.thejokerer.data.database.asDomainJoke
import com.example.thejokerer.model.Joke
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This class contains tests for the [JokeDao] interface.
 */
@RunWith(AndroidJUnit4::class)
class JokeDaoTest {
    // Reference to the DAO and in-memory database
    private lateinit var jokeDao: JokeDao
    private lateinit var jokeDb: JokeDb

    // Sample Jokes for testing
    private var joke1 = Joke("super funny joke", favorite = false)
    private var joke2 = Joke("a less funny joke", favorite = false)

    /**
     * Helper function to add one joke to the database.
     */
    private suspend fun addOneJokeToDb() {
        jokeDao.insert(joke1.asDbJoke())
    }

    /**
     * Helper function to add two jokes to the database.
     */
    private suspend fun addTwoJokesToDb() {
        jokeDao.insert(joke1.asDbJoke())
        jokeDao.insert(joke2.asDbJoke())
    }

    /**
     * Helper function to remove a joke from the database.
     */
    private suspend fun removeJokeFromDb() {
        jokeDao.delete(joke1.asDbJoke())
    }

    /**
     * Set up an in-memory database and DAO before each test.
     */
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        jokeDb = Room.inMemoryDatabaseBuilder(context, JokeDb::class.java)
            .allowMainThreadQueries()
            .build()
        jokeDao = jokeDb.jokeDao()
    }

    /**
     * Close the in-memory database after each test.
     */
    @After
    @Throws(IOException::class)
    fun closeDb() {
        jokeDb.close()
    }

    /**
     * Test to check if [JokeDao.getAllItems] returns all jokes from the database.
     */
    @Test
    @Throws(Exception::class)
    fun daoGetAllJokes_returnsAllJokesFromDB() = runBlocking {
        // Add two jokes to the database
        addTwoJokesToDb()

        // Retrieve all items from the database and assert their equality
        val allItems = jokeDao.getAllItems().first()
        assertEquals(allItems[0].asDomainJoke(), joke1)
        assertEquals(allItems[1].asDomainJoke(), joke2)
    }

    /**
     * Test to check if [JokeDao.insert] inserts a joke into the database.
     */
    @Test
    @Throws(Exception::class)
    fun daoInsert_insertJokeIntoDB() = runBlocking {
        // Add one joke to the database
        addOneJokeToDb()

        // Retrieve all items from the database and assert their equality
        val allItems = jokeDao.getAllItems().first()
        assertEquals(allItems[0].asDomainJoke(), joke1)
    }

    /**
     * Test to check if [JokeDao.delete] removes a joke from the database.
     */
    @Test
    @Throws(Exception::class)
    fun daoRemoveJoke_returnOtherJokeFromDB() = runBlocking {
        // Add two jokes to the database
        addTwoJokesToDb()

        // Remove one joke from the database
        removeJokeFromDb()

        // Retrieve all items from the database and assert the remaining joke's equality
        val allItems = jokeDao.getAllItems().first()
        assertEquals(allItems[0].asDomainJoke(), joke2)
    }
}
