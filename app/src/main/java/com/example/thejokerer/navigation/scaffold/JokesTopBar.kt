package com.example.thejokerer.navigation.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Composable function representing the top bar/header for the application.
 *
 * The top bar composable displays the title of the application
 */
@Composable
fun JokesTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "The Jokerer",
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
