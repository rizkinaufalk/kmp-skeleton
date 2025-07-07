package org.example.kmpskeleton.ui.screen.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.example.kmpskeleton.features.character.CharListComponent
import org.example.kmpskeleton.ui.components.CharListItem
import org.example.kmpskeleton.ui.components.CustomPullToRefreshBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    component: CharListComponent,
    modifier: Modifier = Modifier
) {
    val state by component.uiState.subscribeAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleIndex = visibleItems.lastOrNull()?.index ?: return@collect

                val isAtEnd = lastVisibleIndex >= state.characters.lastIndex
                val canLoadMore = !state.isLoading && !state.endReached

                if (isAtEnd && canLoadMore) {
                    component.nextPage()
                }
            }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when {

            state.isLoading && state.characters.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {

                CustomPullToRefreshBox(
                    isRefreshing = state.isRefreshing,
                    onRefresh = {component.onPullRefresh()},
                    modifier = Modifier
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(
                            items = state.characters,
                            key = { _, item -> item.id ?: item.hashCode() }
                        ) { _, char ->
                            CharListItem(
                                imageUrl = char.image.orEmpty(),
                                name = char.name.orEmpty(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable { component.onCharClicked(char) }
                            )
                        }

                        if (state.isLoading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview() {
    MaterialTheme {
//        CharacterListScreen()
    }
}