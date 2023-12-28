package com.example.thejokerer.pages.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thejokerer.viewmodels.JokeViewModel

@Composable
fun FavoritesPage() {

    val jokeViewModel: JokeViewModel = viewModel(factory = JokeViewModel.Factory)


    Column (modifier = Modifier.fillMaxWidth(),
    ){
        Text(text = "Favorites page")


        jokeViewModel.getFavoriteJokes()
    }

}