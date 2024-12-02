package com.geeks.rickandmortyapp.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.*


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {

    val pages: List<@Composable () -> Unit> = listOf(
        {
            OnboardingPage(
                title = "Welcome!",
                description = "Explore the app and discover amazing features."
            )
        },
        {
            OnboardingPage(
                title = "Get Started",
                description = "Dive into the world of adventure with us."
            )
        },
        {
            OnboardingPageWithButton(
                title = "Ready?",
                description = "Tap below to begin your journey.",
                buttonText = "Click Me",
                onClick = {
                    navController.navigate("character_screen") {
                        popUpTo("onboarding_screen") { inclusive = true }
                    }
                }
            )
        }
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            pages[page]()
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(pages.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            if (pagerState.currentPage == index) Color.Blue else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
