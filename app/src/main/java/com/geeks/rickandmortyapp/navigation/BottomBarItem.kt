package com.geeks.rickandmortyapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.geeks.rickandmortyapp.screen.character.CharacterDetailScreen
import com.geeks.rickandmortyapp.screen.character.CharacterScreen
import com.geeks.rickandmortyapp.screen.episode.EpisodeDetailScreen
import com.geeks.rickandmortyapp.screen.episode.EpisodeScreen
import com.geeks.rickandmortyapp.screen.location.LocationDetailScreen
import com.geeks.rickandmortyapp.screen.location.LocationScreen
import com.geeks.rickandmortyapp.screen.onboarding.OnboardingScreen
import com.geeks.rickandmortyapp.screen.search.SearchScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostSetup(navController: NavController) {
    AnimatedNavHost(
        navController = navController as NavHostController,
        startDestination = "onboarding_screen"
    ) {
        composable(route = "onboarding_screen") {
            OnboardingScreen(navController = navController)
        }

        composable(
            route = BottomBarItem.Episodes.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it / 2 }, animationSpec = tween(1000)) +
                        fadeIn(animationSpec = tween(1000))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it / 2 }, animationSpec = tween(1000)) +
                        fadeOut(animationSpec = tween(1000))
            }
        ) {
            EpisodeScreen(navController = navController)
        }

        composable(
            route = BottomBarItem.Locations.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(1000)) +
                        fadeIn(animationSpec = tween(1000))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(1000)) +
                        fadeOut(animationSpec = tween(1000))
            }
        ) {
            LocationScreen { locationId ->
                navController.navigate("location_detail_screen/$locationId")
            }
        }

        composable(route = BottomBarItem.Characters.route) {
            CharacterScreen(navController = navController)
        }
        composable(route = "search_screen") {
            SearchScreen(navController = navController)
        }

        composable(
            route = "character_detail_screen/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType }),
            enterTransition = {
                slideInVertically(initialOffsetY = { -it }, animationSpec = tween(1000)) +
                        fadeIn(animationSpec = tween(1000))
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { it }, animationSpec = tween(1000)) +
                        fadeOut(animationSpec = tween(1000))
            }
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: -1
            CharacterDetailScreen(characterId = characterId)
        }

        composable(
            route = "episode_detail_screen/{episodeId}",
            arguments = listOf(navArgument("episodeId") { type = NavType.IntType }),
            enterTransition = {
                slideInVertically(initialOffsetY = { it }, animationSpec = tween(1000)) +
                        fadeIn(animationSpec = tween(1000))
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(1000)) +
                        fadeOut(animationSpec = tween(1000))
            }
        ) { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getInt("episodeId") ?: -1
            EpisodeDetailScreen(episodeId = episodeId)
        }

        composable(
            route = "location_detail_screen/{locationId}",
            arguments = listOf(navArgument("locationId") { type = NavType.IntType }),
            enterTransition = {
                scaleIn(initialScale = 0.8f, animationSpec = tween(1000)) +
                        fadeIn(animationSpec = tween(1000))
            },
            exitTransition = {
                scaleOut(targetScale = 1.2f, animationSpec = tween(1000)) +
                        fadeOut(animationSpec = tween(1000))
            }
        ) { backStackEntry ->
            val locationId = backStackEntry.arguments?.getInt("locationId") ?: -1
            LocationDetailScreen(locationId = locationId)
        }
    }
}


sealed class BottomBarItem(val route: String, val label: String) {
    data object Characters : BottomBarItem("character_screen", "Characters")
    data object Episodes : BottomBarItem("episode_screen", "Episodes")
    data object Locations : BottomBarItem("location_screen", "Locations")
}