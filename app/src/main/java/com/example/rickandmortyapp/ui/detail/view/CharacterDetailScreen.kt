package com.example.rickandmortyapp.ui.detail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CharacterDetailScreen(
    name: String,
    status: String,
    species: String,
    gender: String,
    origin: String,
    image: String
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = "Image from URL",
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = name, style = MaterialTheme.typography.h4)
        Text(
            text = "Status: $status",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Specie: $species",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Gender: $gender",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Origin: $origin",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp)
        )
    }
}