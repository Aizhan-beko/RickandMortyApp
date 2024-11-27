package com.geeks.rickandmortyapp.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomBarItem.Characters to Icons.Default.AccountCircle,
        BottomBarItem.Episodes to Icons.Default.Info,
        BottomBarItem.Locations to Icons.Default.LocationOn
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.Yellow
    ) {
        items.forEach { (item, icon) ->

            val iconSize by animateFloatAsState(targetValue = if (currentRoute == item.route) 50f else 36f)
            val iconColor by animateColorAsState(targetValue = if (currentRoute == item.route) Color.Red else Color.Yellow)
            val textRotation by animateFloatAsState(targetValue = if (currentRoute == item.route) 360f else 0f)
            val textColor by animateColorAsState(targetValue = if (currentRoute == item.route) Color.Cyan else Color.White)

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = item.label,
                        modifier = Modifier
                            .size(iconSize.dp)
                            .padding(4.dp),
                        tint = iconColor
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = textColor,
                        modifier = Modifier
                            .graphicsLayer(rotationZ = textRotation)
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}