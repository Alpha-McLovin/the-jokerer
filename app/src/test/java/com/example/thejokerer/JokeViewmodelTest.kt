package com.example.thejokerer
import com.example.thejokerer.data.database.asDomainJokes
import com.example.thejokerer.fake.FakeApiJokeRepository
import com.example.thejokerer.fake.FakeDataSource
import com.example.thejokerer.fake.FakeJokeApiService
import com.example.thejokerer.model.Joke
import com.example.thejokerer.states.JokeApiState
import com.example.thejokerer.viewmodels.JokeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * This class contains unit tests for the [JokeViewModel] class.
 */
class JokeViewmodelTest {

    private lateinit var viewModel: JokeViewModel
    private lateinit var apiState: JokeApiState
    private lateinit var uiList: List<Joke>

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /**
     * Helper function to get the Joke from the current JokeApiState.
     */
    private fun getJoke(): Joke {
        val thisJoke: Joke

        when (apiState) {
            is JokeApiState.Success -> { thisJoke = (apiState as JokeApiState.Success).joke }
            else -> { throw AssertionError() }
        }
        return thisJoke
    }

    /**
     * Initialization method to set up the [JokeViewModel] and related states.
     */
    @Before
    fun init() = runTest {
        viewModel = JokeViewModel(
            jokeRepository = FakeApiJokeRepository(FakeJokeApiService()),
        )

        apiState = viewModel.apiState
        when (apiState) {
            is JokeApiState.Success -> {
                uiList = viewModel.uiListState.first()
            }
            else -> { throw AssertionError() }
        }
    }

    /**
     * Test to verify if the retrieved favorite jokes match the expected favorite jokes.
     */
    @Test
    fun getFavoriteJokesTest() {
        Assert.assertEquals(uiList, FakeDataSource.favoriteJokes.asDomainJokes())
    }

    /**
     * Test to verify if the retrieved joke matches the expected joke.
     */
    @Test
    fun getJokeTest() {
        Assert.assertEquals(getJoke(), FakeDataSource.joke1)
    }

    /**
     * Test to verify if adding a joke to favorites sets its 'favorite' property to true.
     */
    @Test
    fun addFavoriteJoke() {
        viewModel.addFavorite(getJoke())
        Assert.assertEquals(getJoke().favorite, true)
    }

    /**
     * Test to verify if removing a joke from favorites sets its 'favorite' property to false.
     */
    @Test
    fun removeFavoriteJoke() {
        viewModel.removeFavorite(getJoke())
        Assert.assertEquals(getJoke().favorite, false)
    }

    /**
     * Test to verify the flow of adding and removing a joke from favorites.
     */
    @Test
    fun addAndRemoveAJokeToFavorites() {
        addFavoriteJoke()
        removeFavoriteJoke()
    }
}

/**
 * Rule to provide a [TestDispatcher] for testing coroutine dispatchers.
 */
class TestDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    /**
     * Set the test dispatcher as the main dispatcher before each test.
     */
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * Reset the main dispatcher after each test.
     */
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
