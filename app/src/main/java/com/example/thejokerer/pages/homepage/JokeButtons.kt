package com.example.thejokerer.pages.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun JokeButtons(
    getRandomJoke : () -> Unit,
    getItJoke : () -> Unit,
    getDarkJoke : () -> Unit,
    getPun : () -> Unit,
    getMiscellaneous : () -> Unit,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp, top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick =  getRandomJoke  ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Random Joke")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick =  getItJoke ,
                modifier = Modifier.width(150.dp)) {
                Text(text = "IT joke")
            }

            Button(
                onClick =  getMiscellaneous ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Miscellaneous")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick =  getPun ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Pun")
            }

            Button(onClick =  getDarkJoke ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = "Dark Joke")
            }
        }
    }

}
