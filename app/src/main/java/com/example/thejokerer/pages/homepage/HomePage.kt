package com.example.thejokerer.pages.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomePage(){

    Column (modifier = Modifier.fillMaxWidth(),
        ) {
        Text(text = "Greeting Summoner")
    }

}