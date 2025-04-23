package org.example.kmpskeleton.ui.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.kmpskeleton.ui.screen.CharacterDetailScreen

@Composable
fun AndroidApp(
    modifier: Modifier = Modifier
) {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = CharacterDetailScreen
        ) {
            composable<CharacterDetailScreen>{
                CharacterDetailScreen()
            }
        }

    }

}