package com.example.thejokerer

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.thejokerer.navigation.AppStructure
import com.example.thejokerer.navigation.NavigationTypes
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * This class contains UI tests for navigation within the app.
 */
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    /**
     * Set up the AppStructure with a TestNavHostController before each test.
     */
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppStructure(navigationType = NavigationTypes.BOTTOM_NAVIGATION, navController = navController)
        }
    }

    /**
     * Test to verify if the start destination is displayed on launch.
     */
    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("The Jokerer")
            .assertIsDisplayed()
    }

    /**
     * Test to navigate to the favorites screen and verify its display.
     */
    @Test
    fun navigateToFavorite() {
        composeTestRule
            .onNodeWithContentDescription("go to favorites")
            .performClick()
        composeTestRule
            .onNodeWithText("Your Favorite Jokes")
            .assertIsDisplayed()
    }

    /**
     * Test to navigate back to the home screen from the favorites screen.
     */
    @Test
    fun navigateBackToHome() {
        navigateToFavorite()

        composeTestRule
            .onNodeWithContentDescription("go to home")
            .performClick()

        composeTestRule
            .onNodeWithText("Welcome!")
            .assertIsDisplayed()
    }
}
