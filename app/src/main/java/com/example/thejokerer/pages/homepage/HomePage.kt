package com.example.thejokerer.pages.homepage

import android.content.ClipData.Item
import android.content.res.Configuration

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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WifiOff
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thejokerer.R
import com.example.thejokerer.model.Joke
import com.example.thejokerer.pages.shared.ErrorPage
import com.example.thejokerer.pages.shared.LoadingPage
import com.example.thejokerer.pages.shared.Title
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
                LoadingPage()
            }

            JokeApiState.Error -> {
                ErrorPage()
            }

            JokeApiState.NoInternet -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row (modifier = Modifier.fillMaxWidth()){
                        Icon(
                            Icons.Default.WifiOff,
                            contentDescription = stringResource(R.string.no_internet_icon),
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .size(dimensionResource(id = R.dimen.notif_size)),
                        )
                        Text(
                            text = stringResource(R.string.no_internet_message),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }

                    Button(
                        onClick = { jokeViewModel.getApiJoke() } ,
                        modifier = Modifier
                            .width(120.dp)
                            .padding(top = 10.dp)
                    ) {
                        Row( modifier = Modifier.fillMaxWidth() ,
                            horizontalArrangement = Arrangement.SpaceBetween ) {
                            Icon(Icons.Default.Refresh , contentDescription = stringResource(R.string.refresh_icon))
                            Text(text = stringResource(R.string.refresh_message))
                        }
                    }
                }
            }

            is JokeApiState.Success -> {

                val configuration = LocalConfiguration.current
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> {
                        Row {

                            JokeBox(
                                modifier = Modifier.fillMaxWidth(0.7f),
                                joke = apiState.joke,
                                isFavorite = isFavorite,
                                likeJoke = { jokeViewModel.addFavorite(apiState.joke) },
                                dislikeJoke = { jokeViewModel.removeFavorite(apiState.joke) }
                            )

                            JokeButtonsLandscape(
                                getRandomJoke = { jokeViewModel.getApiJoke() },
                                getItJoke = { jokeViewModel.getApiItJoke() },
                                getDarkJoke = { jokeViewModel.getApiDarkJoke() },
                                getPun = { jokeViewModel.getApiPun() },
                                getMiscellaneous = { jokeViewModel.getApiMiscelleaneous()}
                            )
                        }
                    }

                    else -> {
                        LazyColumn (modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            item {
                                Title(text = stringResource(R.string.home_page_title))
                                Text(
                                    text = stringResource(R.string.home_page_introduction),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                JokeBox(
                                    modifier = Modifier.fillMaxWidth(),
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
    }
}




