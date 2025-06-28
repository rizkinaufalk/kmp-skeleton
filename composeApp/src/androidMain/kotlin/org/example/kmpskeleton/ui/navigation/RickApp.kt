package org.example.kmpskeleton.ui.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.kmpskeleton.navigation.RootComponent
import org.example.kmpskeleton.ui.components.toolbar.ToolbarConfig

//@Composable
//fun RickApp(
//    widthSizeClass: WindowWidthSizeClass,
//    modifier: Modifier = Modifier
//) {
//    MaterialTheme {
//        val navController = rememberNavController()
//        val navigationAction = remember(navController) {
//            RickAppNavigationActions(navController)
//        }
//
////        ModalNavigationDrawer(
////            drawerContent =
////        ) { }
//
//        NavHost(
//            navController = navController,
//            startDestination = CharacterDetailScreen
//        ) {
//            composable<CharacterDetailScreen>{
//                CharacterDetailScreen()
//            }
//        }
//
//    }
//
//}


@Composable
fun RickApp(
    rootComponent: RootComponent,
    updateToolbar: (ToolbarConfig) -> Unit
) {
    MaterialTheme {
        RootContent(
            component = rootComponent,
            modifier = Modifier.fillMaxSize(),
            updateToolbar = updateToolbar
        )
    }
}