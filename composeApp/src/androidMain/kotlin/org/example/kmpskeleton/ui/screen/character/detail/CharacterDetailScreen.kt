package org.example.kmpskeleton.ui.screen.character.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.aakira.napier.Napier
import org.example.kmpskeleton.features.UiState
import org.example.kmpskeleton.features.character.details.CharDetailComponent
import org.example.kmpskeleton.ui.components.CharacterHeader
import org.example.kmpskeleton.ui.components.RowTitleDesc
import org.koin.compose.viewmodel.koinViewModel

    @Composable
    fun CharacterDetailScreen(
        component: CharDetailComponent,
        modifier: Modifier = Modifier
    ) {
        val uiState by component.uiState.subscribeAsState()

        Surface(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background) // Optional: to ensure contrast
        ) {
            Column {
                CharacterHeader(
                    imageUrl = uiState.character?.image.orEmpty(),
                    name = uiState.character?.name.orEmpty()
                )

                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ){

                    RowTitleDesc("Status", uiState.character?.status.orEmpty())
                    RowTitleDesc("Species", uiState.character?.species.orEmpty())
                    RowTitleDesc("Gender", uiState.character?.gender.orEmpty())
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun CharacterDetailScreenPreview() {
    MaterialTheme {
//        CharacterDetailScreen()
    }
}