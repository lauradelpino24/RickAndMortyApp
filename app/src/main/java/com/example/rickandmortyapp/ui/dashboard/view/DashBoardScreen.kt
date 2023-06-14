package com.example.rickandmortyapp.ui.dashboard.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmortyapp.data.network.model.Results
import com.example.rickandmortyapp.domain.common.Status
import com.example.rickandmortyapp.model.Routes
import com.example.rickandmortyapp.ui.dashboard.viewmodel.DashBoardViewModel
import java.net.URLEncoder

@Composable
fun DashBoardScreen(dashBoardViewModel: DashBoardViewModel, navController: NavController) {

    Column {
        SearchBar(dashBoardViewModel, navController)
    }
}

@Composable
fun SearchBar(dashBoardViewModel: DashBoardViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                dashBoardViewModel.search(query = searchQuery)
            },
            label = { Text("Search", color = Color.Black) },
            leadingIcon = { Icon(Icons.Default.Search, "Search Icon") },
            textStyle = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = MaterialTheme.colors.primary,
                focusedBorderColor = Color(0xFFb2df28),
                unfocusedBorderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                backgroundColor = Color(0xFFFAFAFA),
            ),
        )

    }

    val searchResultsState by dashBoardViewModel.stateList.collectAsState()

    when (searchResultsState.responseType) {
        Status.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color(0xFFb2df28)
                )
            }
        }

        Status.ERROR -> {
            Text(
                "An error has occurred. Try again  ",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.White
            )
        }

        else -> {
            val results = searchResultsState.data ?: emptyList()
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                itemsIndexed(results) { _, character ->
                    CharacterItem(character = character) {
                        val encodedImage = URLEncoder.encode(character.image, "UTF-8")

                        navController.navigate(
                            Routes.CharacterDetail.createRoute(
                                character.name,
                                character.status,
                                character.species,
                                character.gender,
                                character.origin?.name ?: "undefined",
                                encodedImage
                            )
                        )
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterItem(character: Results, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = character.type,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
