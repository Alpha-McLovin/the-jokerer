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

@Composable
fun JokesNavigationRail(showHomePage: () -> Unit, showFavoritePage: () -> Unit) {
    NavigationRail {
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
