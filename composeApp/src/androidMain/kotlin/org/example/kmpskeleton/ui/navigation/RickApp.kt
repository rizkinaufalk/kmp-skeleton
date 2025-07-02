package org.example.kmpskeleton.ui.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import org.example.kmpskeleton.navigation.RootComponent
import org.example.kmpskeleton.ui.components.toolbar.ToolbarConfig
import org.example.kmpskeleton.ui.screen.character.CharacterListScreen
import org.example.kmpskeleton.ui.screen.character.detail.CharacterDetailScreen


@Composable
fun RickApp(
    rootComponent: RootComponent,
    updateToolbar: (ToolbarConfig) -> Unit
) {
    Children(
        stack = rootComponent.routerState,
        animation = stackAnimation(slide())
    ) {
        val child = it.instance

        when (child) {
            is RootComponent.Child.CharList -> CharacterListScreen(child.component)
            is RootComponent.Child.CharDetail -> CharacterDetailScreen(child.component)
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