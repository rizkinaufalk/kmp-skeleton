package org.example.kmpskeleton.ui.screen.character.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.aakira.napier.Napier
import org.example.kmpskeleton.features.character.favourites.FavoritesComponent
import org.example.kmpskeleton.ui.components.CharListItem

@Composable
fun FavortiesScreen(
    component: FavoritesComponent,
    modifier: Modifier = Modifier
){

    val state by component.uiState.subscribeAsState()
    val listState = rememberLazyListState()

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            state.characters.isNotEmpty() -> {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(
                        items = state.characters,
                        key = { _, item -> item.id ?: item.hashCode() }
                    ) { index, char ->
                        CharListItem(
                            imageUrl = char.image.orEmpty(),
                            name = char.name.orEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { component.onCharClicked(char) }
                        )
                    }
                }
            }

            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No favorites found")
                }
            }
        }
    }


}