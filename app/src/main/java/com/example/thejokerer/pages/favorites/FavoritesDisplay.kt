package com.example.thejokerer.pages.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.thejokerer.R
import com.example.thejokerer.model.Joke

@Composable
fun FavoritesDisplay(joke: Joke, deleteJoke: () -> Unit) {
    var isVisible by rememberSaveable { mutableStateOf(false) }
    val dismiss = { isVisible = false }
    if (isVisible) {
        Dialog(onDismissRequest = dismiss) {
            Card(
                shape = RoundedCornerShape(30.dp),
            ) {
                Column(
                    modifier = Modifier
                        .width(250.dp)
                        .height(180.dp)
                        .background(MaterialTheme.colorScheme.primary),
                ) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = stringResource(R.string.confirmation_message),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )

                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Button(
                            onClick = {
                                deleteJoke()
                                dismiss()
                            },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
                        ) {
                            Text(
                                text = stringResource(R.string.yes),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }

                        Button(
                            onClick = dismiss,
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
                        ) {
                            Text(
                                text = stringResource(R.string.no),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            }
        }
    }

    FavoriteJokeBox(joke = joke, showConfirmation = { isVisible = true })
}
