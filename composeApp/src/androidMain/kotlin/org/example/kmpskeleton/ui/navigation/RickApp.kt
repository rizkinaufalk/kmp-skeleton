package org.example.kmpskeleton.ui.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.kmpskeleton.navigation.RootComponent
import org.example.kmpskeleton.ui.components.toolbar.ToolbarConfig
import org.example.kmpskeleton.ui.screen.character.CharacterListScreen
import org.example.kmpskeleton.ui.screen.character.detail.CharacterDetailScreen
import org.example.kmpskeleton.ui.screen.character.favorites.FavortiesScreen
import org.example.kmpskeleton.ui.screen.character.settings.SettingsScreen


@Composable
fun RickApp(
    rootComponent: RootComponent,
    updateToolbar: (ToolbarConfig) -> Unit
) {
    val childStack by rootComponent.routerState.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide())
    ) { child ->

        when (val instance = child.instance) {
            is RootComponent.Child.CharList -> CharacterListScreen(instance.component)
            is RootComponent.Child.CharDetail -> CharacterDetailScreen(instance.component)
            is RootComponent.Child.Favorites -> FavortiesScreen(instance.component)
            is RootComponent.Child.Settings -> { SettingsScreen(instance.component)}
        }
    }

    MaterialTheme {
        RootContent(
            component = rootComponent,
            modifier = Modifier.fillMaxSize(),
            updateToolbar = updateToolbar
        )
    }
}