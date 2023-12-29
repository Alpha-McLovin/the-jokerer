package com.example.thejokerer.pages.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thejokerer.R
import com.example.thejokerer.model.Joke

@Composable
fun JokeBox(modifier: Modifier, joke: Joke, isFavorite: Boolean, likeJoke: () -> Unit, dislikeJoke: () -> Unit) {
    Box(
        modifier =
        modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center,
    ) {
        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 10.dp),
            ) {
                Text(
                    text = joke.joke,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

            Column(
                modifier = Modifier.padding(start = 3.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                if (isFavorite) {
                    IconButton(onClick = dislikeJoke) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = stringResource(R.string.dislike_button),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                        )
                    }
                } else {
                    IconButton(onClick = likeJoke) {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.like_button),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size)),
                        )
                    }
                }
            }
        }
    }
}
