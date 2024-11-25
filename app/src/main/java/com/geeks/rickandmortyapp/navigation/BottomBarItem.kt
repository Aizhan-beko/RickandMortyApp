package com.geeks.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.geeks.rickandmortyapp.screen.character.CharacterDetailScreen
import com.geeks.rickandmortyapp.screen.character.CharacterScreen
import com.geeks.rickandmortyapp.screen.episode.EpisodeDetailScreen
import com.geeks.rickandmortyapp.screen.episode.EpisodeScreen
import com.geeks.rickandmortyapp.screen.location.LocationDetailScreen
import com.geeks.rickandmortyapp.screen.location.LocationScreen

@Composable
fun NavHostSetup(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomBarItem.Characters.route
    ) {
        composable(BottomBarItem.Characters.route) {
            CharacterScreen(navController = navController)

        }
        composable(BottomBarItem.Episodes.route) {
            EpisodeScreen(navController = navController)
        }

        composable(BottomBarItem.Locations.route) {
            LocationScreen { locationId ->
                navController.navigate("location_detail_screen/$locationId")
            }
        }
        composable(
            "episode_detail_screen/{episodeId}",
            arguments = listOf(navArgument("episodeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getInt("episodeId") ?: -1
            EpisodeDetailScreen(episodeId = episodeId)
        }
        composable(
            "character_detail_screen/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: -1
            CharacterDetailScreen(characterId = characterId)
        }
        composable(
            "location_detail_screen/{locationId}",
            arguments = listOf(navArgument("locationId") { type = NavType.IntType })
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