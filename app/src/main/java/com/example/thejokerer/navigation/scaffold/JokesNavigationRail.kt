package com.example.thejokerer.navigation.scaffold

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.thejokerer.R

/**
 * Composable function representing a custom navigation rail for navigation in landscape mode.
 *
 * The `JokesNavigationRail` composable displays two navigation items - one for navigating to the home page
 * and another for navigating to the favorites page. Clicking on each item triggers the
 * corresponding action provided through the `showHomePage` and `showFavoritePage` parameters.
 *
 * @param showHomePage Callback function to be invoked when the home page navigation item is clicked.
 * @param showFavoritePage Callback function to be invoked when the favorites page navigation item is clicked.
 */
@Composable
fun JokesNavigationRail(showHomePage: () -> Unit, showFavoritePage: () -> Unit) {
    NavigationRail {

        // Home Page Navigation Item
        NavigationRailItem(
            selected = false,
            onClick = showHomePage,
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.appbar_icon_home_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            },
        )

        // Favorites Page Navigation Item
        NavigationRailItem(
            selected = false,
            onClick = showFavoritePage,
            icon = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = stringResource(R.string.appbar_icon_favorites_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            },
        )
    }
}
