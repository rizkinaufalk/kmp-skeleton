package org.example.kmpskeleton.ui.screen.character.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.aakira.napier.Napier
import org.example.kmpskeleton.features.settings.SettingsComponent
import org.example.kmpskeleton.ui.components.ButtonMain

@Composable
fun SettingsScreen(
    component: SettingsComponent,
    modifier: Modifier = Modifier
) {

    val state by component.settingsUIState.subscribeAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isDeleting -> {
                    CircularProgressIndicator()
                }

                else -> {
                    ButtonMain(
                        title = "Delete All Favorites",
                        onClick = { component.onDeleteAllFavClicked() }
                    )
                }
            }
        }
    }
}