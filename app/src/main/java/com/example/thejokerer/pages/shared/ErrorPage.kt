package com.example.thejokerer.pages.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thejokerer.R

@Composable
fun ErrorPage(){
    Row (modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)
    ) {
        Icon(
            Icons.Default.Error,
            contentDescription = stringResource(R.string.error_icon),
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(dimensionResource(id = R.dimen.notif_size)),
        )
        Text(
            text = stringResource(R.string.error_message),
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}