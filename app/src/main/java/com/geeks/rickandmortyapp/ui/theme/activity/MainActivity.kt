package com.geeks.rickandmortyapp.ui.theme.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.geeks.rickandmortyapp.screen.main.MainScreen
import com.geeks.rickandmortyapp.ui.theme.RickandMortyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyAppTheme {
                MainScreen()
            }
        }
    }
}