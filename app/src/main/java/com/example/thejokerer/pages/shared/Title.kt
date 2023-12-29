package com.example.thejokerer.pages.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thejokerer.R

@Composable
fun Title(text : String){
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = MaterialTheme.colorScheme.secondary
    )
}