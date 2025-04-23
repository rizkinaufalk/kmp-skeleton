package org.example.kmpskeleton.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.aakira.napier.Napier
import org.example.kmpskeleton.features.UiState
import org.example.kmpskeleton.features.character.details.CharacterDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterDetailScreen (
){
    val viewModel = koinViewModel<CharacterDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCharacterById(2)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Text(text = "This is Character Detail Screen")
        when (uiState) {
            UiState.Loading -> Napier.d ("TestingApp : Loading State")
            is UiState.Success -> Napier.d ("TestingApp : Success")
            is UiState.Error -> Napier.d ("TestingApp : Error")
        }
    }
}