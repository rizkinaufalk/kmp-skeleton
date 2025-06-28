package org.example.kmpskeleton.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.kmpskeleton.navigation.RootComponent
import org.example.kmpskeleton.ui.screen.character.CharacterListScreen
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import org.example.kmpskeleton.ui.components.toolbar.ToolbarConfig
import org.example.kmpskeleton.ui.screen.character.detail.CharacterDetailScreen

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
    updateToolbar: (ToolbarConfig) -> Unit
) {
    Children(
        stack = component.routerState,
        modifier = modifier,
        animation = stackAnimation(slide())
    )
    {
        when (val child = it.instance) {
            is RootComponent.Child.CharList -> {
                updateToolbar(
                    ToolbarConfig(
                        title = "Characters",
                        showBack = false
                    )
                )
                CharacterListScreen(child.component)
            }
            is RootComponent.Child.CharDetail -> {
                updateToolbar(
                    ToolbarConfig(
                        title = "Character Detail",
                        showBack = true
                    )
                )
                CharacterDetailScreen(child.component)
            }
        }
    }
}
