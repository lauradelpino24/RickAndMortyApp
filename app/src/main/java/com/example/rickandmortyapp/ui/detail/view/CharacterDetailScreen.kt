package com.example.rickandmortyapp.ui.detail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyapp.model.Routes

@Composable
fun CharacterDetailScreen(
    name: String,
    status: String,
    species: String,
    gender: String,
    origin: String,
    image: String,
    navController: NavController
) {

    Box() {
        IconButton(
            onClick = { navController.navigate(Routes.DashBoard.route) },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFFb2df28),
                modifier = Modifier.size(24.dp)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = "Image from URL",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 50.dp)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.h3.copy(color = Color(0xFF35c9dd)),
            modifier = Modifier.padding(top = 25.dp, start = 16.dp, end = 16.dp, bottom = 25.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color.Green,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Status: $status",
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(
                    text = "Specie: $species",
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(
                    text = "Gender: $gender",
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(
                    text = "Origin: $origin",
                    modifier = Modifier.padding(vertical = 16.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }

    }
}