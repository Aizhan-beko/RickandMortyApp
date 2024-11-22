package com.geeks.rickandmortyapp.screen.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel


@Composable
fun LocationDetailScreen(
    locationId: Int,
    viewModel: LocationsViewModel = koinViewModel()
) {
    val location by viewModel.selectedLocation.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getLocationById(locationId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        if (location == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            location?.let {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Name: ${it.name}",
                        fontSize = 25.sp,
                        color = Color.Yellow,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Type: ${it.type}",
                        fontSize = 25.sp,
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Dimension: ${it.dimension}",
                        fontSize = 25.sp,
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Residents: ${it.residents.size} characters",
                        fontSize = 25.sp,
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold

                    )

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(it.residents) { residentUrl ->
                            Text(
                                text = residentUrl,
                                fontSize = 15.sp,
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}