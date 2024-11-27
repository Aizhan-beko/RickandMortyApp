package com.geeks.rickandmortyapp.screen.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.geeks.rickandmortyapp.navigation.BottomBarItem
import com.geeks.rickandmortyapp.navigation.BottomNavigationBar
import com.geeks.rickandmortyapp.navigation.NavHostSetup
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val showWelcomeText = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(5000)
        showWelcomeText.value = false
    }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val offsetX by animateFloatAsState(
        targetValue = if (showWelcomeText.value) -1000f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    val offsetY by animateFloatAsState(
        targetValue = if (showWelcomeText.value) 0f else 10f,
        animationSpec = tween(durationMillis = 500, delayMillis = 500)
    )

    val textColor by animateColorAsState(
        targetValue = if (showWelcomeText.value) Color.Gray else Color.Yellow,
        animationSpec = repeatable(
            iterations = 1000,
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (showWelcomeText.value) {
                                "Welcome to Rick and Morty App"
                            } else {
                                getTitleForRoute(currentRoute)
                            },
                            fontSize = 25.sp,
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .offset(x = offsetX.dp, y = offsetY.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHostSetup(navController = navController)
        }
    }
}

@Composable
fun getTitleForRoute(currentRoute: String?): String {
    return when (currentRoute) {
        BottomBarItem.Characters.route -> "Characters"
        BottomBarItem.Episodes.route -> "Episodes"
        BottomBarItem.Locations.route -> "Locations"
        else -> "Rick and Morty App"
    }
}
