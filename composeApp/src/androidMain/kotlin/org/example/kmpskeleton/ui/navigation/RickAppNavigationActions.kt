package org.example.kmpskeleton.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object RickAppDestinations {
    const val CHAR_LIST_ROUTE = "character list"
    const val CHAR_DETAIL_ROUTE = "character detail"
}

class RickAppNavigationActions(navHostController: NavHostController) {

    val navigateToCharList: () -> Unit = {
        navHostController.navigate(RickAppDestinations.CHAR_LIST_ROUTE){
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToCharDetail: () -> Unit = {
        navHostController.navigate(RickAppDestinations.CHAR_DETAIL_ROUTE){
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}