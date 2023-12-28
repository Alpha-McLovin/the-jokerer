package com.example.thejokerer.pages.homepage

import android.content.ClipData.Item
import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.End
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thejokerer.R
import com.example.thejokerer.model.Joke
import com.example.thejokerer.states.JokeApiState
import com.example.thejokerer.viewmodels.JokeViewModel

@Composable
fun HomePage(){

    val jokeViewModel: JokeViewModel = viewModel(factory = JokeViewModel.Factory)
    val apiState = jokeViewModel.apiState
    val isFavorite = jokeViewModel.isFavoriteState

    Column (modifier = Modifier.fillMaxWidth()) {

        when (apiState) {
            is JokeApiState.Loading -> {
                Text(text = "Hold on we're loading in a joke...")
            }

            JokeApiState.Error -> {
                Text(text = "Oops, something went wrong... Maybe this spaghetti code is the Joke after all?")
            }

            JokeApiState.NoInternet -> {
                Text(text = "No internet connection. Please connect so that you can generate jokes")
            }

            is JokeApiState.Success -> {
                LazyColumn (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {


                    
                    item {

                        Text(
                            text = "Welcome!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Enjoy some of our most funniest jokes and take a laughing break.",
                            color = MaterialTheme.colorScheme.secondary
                        )
                        JokeBox(
                            joke = apiState.joke,
                            isFavorite = isFavorite,
                            likeJoke = { jokeViewModel.addFavorite(apiState.joke) },
                            dislikeJoke = { jokeViewModel.removeFavorite(apiState.joke) }
                        )
                    }

                    item {
                        JokeButtons(
                            getRandomJoke = { jokeViewModel.getApiJoke() },
                            getItJoke = { jokeViewModel.getApiItJoke() },
                            getDarkJoke = { jokeViewModel.getApiDarkJoke() },
                            getPun = { jokeViewModel.getApiPun() },
                            getMiscellaneous = { jokeViewModel.getApiMiscelleaneous() }
                        )
                    }
                }
            }
        }
    }
}




