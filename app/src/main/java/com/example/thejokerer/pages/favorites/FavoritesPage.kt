package com.example.thejokerer.pages.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thejokerer.R
import com.example.thejokerer.pages.shared.ErrorPage
import com.example.thejokerer.pages.shared.LoadingPage
import com.example.thejokerer.pages.shared.Title
import com.example.thejokerer.states.FavoriteState
import com.example.thejokerer.viewmodels.JokeViewModel

@Composable
fun FavoritesPage() {

    val jokeViewModel: JokeViewModel = viewModel(factory = JokeViewModel.Factory)
    val favoriteState = jokeViewModel.favoriteState

    Column (modifier = Modifier.fillMaxSize(),
    ){

        when(favoriteState) {
            is FavoriteState.Loading -> {
                LoadingPage()
            }

            is FavoriteState.Error -> {
                ErrorPage()
            }

            is FavoriteState.Success -> {
                val jokeList = jokeViewModel.uiListState.collectAsState().value.reversed()
                if (jokeList.isEmpty()){
                    Title(text = stringResource(R.string.favorite_jokes_title))
                    Row (modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                    ) {
                        Icon(
                            Icons.Default.FolderOpen,
                            contentDescription = stringResource(R.string.favorites_empy_icon),
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .size(dimensionResource(id = R.dimen.notif_size)),
                        )
                        Text(
                            text = stringResource(R.string.favorites_empty_message),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                } else {
                    LazyColumn(){
                        item {
                            Title(text = stringResource(R.string.favorite_jokes_title))
                        }
                        items(jokeList) {
                                joke ->  FavoritesDisplay(joke = joke, deleteJoke = { jokeViewModel.removeFavorite(joke) })
                        }
                    }
                }
            }
        }
    }
}