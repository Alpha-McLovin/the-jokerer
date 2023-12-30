package com.example.thejokerer.navigation.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.thejokerer.R

/**
 * Composable function representing a custom bottom app bar.
 *
 * The `JokesBottomBar` composable displays two icons - one for navigating to the home page
 * and another for navigating to the favorites page. Clicking on each icon triggers the
 * corresponding action provided through the `showHomePage` and `showFavoritePage` parameters.
 *
 * @param showHomePage Callback function to be invoked when the home page icon is clicked.
 * @param showFavoritePage Callback function to be invoked when the favorites page icon is clicked.
 */
@Composable
fun JokesBottomBar(showHomePage: () -> Unit, showFavoritePage: () -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {

            // Home Page Navigation Item
            IconButton(onClick = showHomePage) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.appbar_icon_home_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            }

            // Favorites Page Navigation Item
            IconButton(onClick = showFavoritePage) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = stringResource(R.string.appbar_icon_favorites_description),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )
            }
        }
    }
}
