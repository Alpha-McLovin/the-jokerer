package com.example.thejokerer.pages.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thejokerer.R
import com.example.thejokerer.states.JokeApiState
import com.example.thejokerer.viewmodels.JokeViewModel

@Composable
fun HomePage(){

    val jokeViewModel: JokeViewModel = viewModel(factory = JokeViewModel.Factory)
    val apiState = jokeViewModel.apiState


    Column (modifier = Modifier.fillMaxWidth(),
        ) {
        Text(text = "Greeting Summoner")


            IconButton(onClick = { jokeViewModel.getApiJoke()}) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = "haal op",
                    modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                )

            }
        
        when (apiState){
            is JokeApiState.Loading -> {
                Text(text = "Loading")
            }

            JokeApiState.Error -> {
                Text(text = "error")
            }
            JokeApiState.NoInternet -> {
                Text(text = "geen internet")
            }
            is JokeApiState.Success -> {
               Text(text = apiState.joke.joke)
            }
        }

        IconButton(onClick = { jokeViewModel.getApiJoke()}) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "like",
                modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
            )

        }
        

    }

}