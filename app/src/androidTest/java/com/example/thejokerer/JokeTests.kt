package com.example.thejokerer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.thejokerer.model.Joke
import com.example.thejokerer.pages.homepage.JokeBox
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * This class contains UI tests for the [JokeBox] composable.
 */
class JokeTests {

    // The Joke instance used for testing
    private var joke by mutableStateOf(Joke("this is a test joke"))

    // ComposeTestRule for UI testing
    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Initialization function executed before each test.
     * It sets up the [JokeBox] composable with the initial [joke].
     */
    @Before
    fun init() {
        composeTestRule.setContent {
            JokeBox(
                joke = joke,
                isFavorite = joke.favorite,
                likeJoke = { joke = joke.copy(favorite = true) },
                dislikeJoke = { joke = joke.copy(favorite = false) },
            )
        } 
    }

    /**
     * Test to verify if the displayed joke matches the initial [joke].
     */
    @Test
    fun isJokeDisplayed() {
        composeTestRule
            .onNodeWithText(joke.joke)
            .assertIsDisplayed()
    }

    /**
     * Test to check if the like button changes to a dislike button after clicking.
     */
    @Test
    fun likeJoke() {
        // Assert that the initial joke is displayed
        composeTestRule
            .onNodeWithText(joke.joke)
            .assertIsDisplayed()

        // Click on the like button
        composeTestRule
            .onNodeWithContentDescription("Like Button")
            .performClick()

        // Assert that the dislike button is displayed and like button does not exist
        composeTestRule
            .onNodeWithContentDescription("Dislike Button")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Like Button")
            .assertDoesNotExist()
    }

    /**
     * Testing if the dislike button changes into a like button again after disliking
     */
    @Test
    fun dislikeJoke() {
        // Perform like operation
        likeJoke()

        // Click on the dislike button
        composeTestRule
            .onNodeWithContentDescription("Dislike Button")
            .performClick()

        // Assert that the like button is displayed and dislike button does not exist
        composeTestRule
            .onNodeWithContentDescription("Like Button")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Dislike Button")
            .assertDoesNotExist()
    }
} 

