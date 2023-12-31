package com.example.thejokerer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.thejokerer.navigation.AppStructure
import com.example.thejokerer.navigation.NavigationTypes
import com.example.thejokerer.ui.theme.TheJokererTheme

/**
 * The main activity for the Joke Application.
 *
 * The `MainActivity` class extends [ComponentActivity] and serves as the entry point for the Joke Application.
 * It sets up the Compose UI using the `setContent` function and determines the layout structure based on the
 * window size.
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TheJokererTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val windowSize = calculateWindowSizeClass(activity = this)

                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            AppStructure(NavigationTypes.BOTTOM_NAVIGATION)
                        }

                        else -> {
                            AppStructure(NavigationTypes.NAVIGATION_RAIL)
                        }
                    }
                }
            }
        }
    }
}
