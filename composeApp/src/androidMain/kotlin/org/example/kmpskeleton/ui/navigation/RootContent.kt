package org.example.kmpskeleton.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.kmpskeleton.navigation.RootComponent
import org.example.kmpskeleton.ui.components.toolbar.ToolbarConfig
import org.example.kmpskeleton.ui.screen.character.CharacterListScreen
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
                val state by child.component.uiState.subscribeAsState()
                updateToolbar(
                    ToolbarConfig(
                        title = "Character Detail",
                        showBack = true,
                        actions = {
                            IconButton(onClick = { child.component.onToggleFavorite() }) {
                                if (state.isFavorited) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorite",
                                        tint = Color.Red
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorite"
                                    )
                                }
                            }
                        }
                    )
                )
                CharacterDetailScreen(child.component)
            }
        }
    }
}
