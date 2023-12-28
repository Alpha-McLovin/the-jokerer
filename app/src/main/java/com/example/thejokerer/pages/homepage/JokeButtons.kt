package com.example.thejokerer.pages.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thejokerer.R

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
                Text(text = stringResource(R.string.random_joke))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick =  getItJoke ,
                modifier = Modifier.width(150.dp)) {
                Text(text = stringResource(R.string.it_joke))
            }

            Button(
                onClick =  getMiscellaneous ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.miscellaneous))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick =  getPun ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.pun))
            }

            Button(onClick =  getDarkJoke ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.dark_joke))
            }
        }
    }
}

@Composable
fun JokeButtonsLandscape(
    getRandomJoke : () -> Unit,
    getItJoke : () -> Unit,
    getDarkJoke : () -> Unit,
    getPun : () -> Unit,
    getMiscellaneous : () -> Unit,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

            Button(
                onClick =  getRandomJoke  ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.random_joke))
            }

            Button(
                onClick =  getItJoke ,
                modifier = Modifier.width(150.dp)) {
                Text(text = stringResource(R.string.it_joke))
            }

            Button(
                onClick =  getMiscellaneous ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.miscellaneous))
            }

            Button(onClick =  getPun ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.pun))
            }

            Button(onClick =  getDarkJoke ,
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(R.string.dark_joke))
            }
    }
}
