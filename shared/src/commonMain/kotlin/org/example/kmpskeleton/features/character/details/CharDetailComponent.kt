package org.example.kmpskeleton.features.character.details

import com.arkivanov.decompose.value.Value
import org.example.kmpskeleton.data.remote.entity.CharacterEntity

interface CharDetailComponent {
    val uiState: Value<CharDetailUIState>
    fun onToggleFavorite()
    fun onBackPressed()
}