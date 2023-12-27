package com.example.thejokerer.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thejokerer.R
import com.example.thejokerer.navigation.scaffold.JokesBottomBar
import com.example.thejokerer.navigation.scaffold.JokesNavigationRail
import com.example.thejokerer.navigation.scaffold.JokesTopBar
import com.example.thejokerer.pages.favorites.FavoritesPage
import com.example.thejokerer.pages.homepage.HomePage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun AppStructure(
    navigationType: NavigationTypes,
    navController: NavHostController = rememberNavController(),
    onLogout: () -> Unit = {},
) {
    // val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    if (navigationType == NavigationTypes.BOTTOM_NAVIGATION) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        JokesTopBar()
                    },
                )
            },
            bottomBar = {
                JokesBottomBar(
                    showHomePage = { if (canNavigate(currentBackStack, Destinations.Home.name)) navController.navigate(Destinations.Home.name) },
                    showFavoritePage = { if (canNavigate(currentBackStack, Destinations.Favorites.name)) navController.navigate(Destinations.Favorites.name) },
                )
            },
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Destinations.Home.name,
                modifier = Modifier.padding(innerPadding).padding(horizontal = dimensionResource(R.dimen.padding_screen_borders)),
            ) {

                composable(
                    Destinations.Home.name,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300),
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300),
                        )
                    },
                ) {
                    HomePage()
                }
                composable(
                    Destinations.Favorites.name,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300),
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300),
                        )
                    },
                ) {
                    FavoritesPage()
                }
            }
        }
    } else {
        Row {
            AnimatedVisibility(visible = navigationType == NavigationTypes.NAVIGATION_RAIL) {
                JokesNavigationRail(
                    showHomePage = { if (canNavigate(currentBackStack, Destinations.Home.name)) navController.navigate(Destinations.Home.name) },
                    showFavoritePage = { if (canNavigate(currentBackStack, Destinations.Favorites.name)) navController.navigate(Destinations.Favorites.name) },
                )
            }
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            JokesTopBar()
                        },
                    )
                },

                ) { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Destinations.Home.name,
                    modifier = Modifier.padding(innerPadding).padding(horizontal = dimensionResource(R.dimen.padding_screen_borders)),
                ) {

                    composable(
                        Destinations.Home.name,
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(300),
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down,
                                animationSpec = tween(300),
                            )
                        },
                    ) {
                        HomePage()
                    }
                    composable(
                        Destinations.Favorites.name,
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Down,
                                animationSpec = tween(300),
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Up,
                                animationSpec = tween(300),
                            )
                        },
                    ) {
                        FavoritesPage()
                    }
                }
            }
        }
    }
}
