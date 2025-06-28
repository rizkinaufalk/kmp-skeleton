package org.example.kmpskeleton.ui.screen.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.aakira.napier.Napier
import org.example.kmpskeleton.features.character.CharListComponent
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import org.example.kmpskeleton.ui.components.CharListItem

@Composable
fun CharacterListScreen(
    component: CharListComponent,
    modifier: Modifier = Modifier
) {
    val state by component.uiState.subscribeAsState()
    val listState = rememberLazyListState()

    Surface (
        modifier = modifier.fillMaxSize()
    ){

       when {
           state.isLoading || state.endReached -> {
//               CircularProgressIndicator()
               Napier.d("Is Loading or Reach the end of page", tag = "Loading State")
           }

           else -> {

               LazyColumn(
                   state = listState,
                   modifier = Modifier.fillMaxSize()) {
                   itemsIndexed(
                       items = state.characters,
                       key = {_, item -> item.id ?: item.hashCode()}) { index, char ->

                       CharListItem(
                           imageUrl = char.image.orEmpty(),
                           name = char.name.orEmpty(),
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(vertical = 4.dp)
                               .clickable { component.onCharClicked(char) }
                       )

                       if (index == state.characters.lastIndex && !state.isLoading && !state.endReached) {
                           LaunchedEffect(Unit) {
                               component.nextPage()
                           }
                       }
                   }

                   if (state.isLoading && state.characters.isNotEmpty()) {
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

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview(){
    MaterialTheme {
//        CharacterListScreen()
    }
}