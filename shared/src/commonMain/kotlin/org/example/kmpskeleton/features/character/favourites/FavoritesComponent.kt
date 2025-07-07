package org.example.kmpskeleton.features.character.favourites

import com.arkivanov.decompose.value.Value
import org.example.kmpskeleton.data.remote.entity.CharacterEntity

interface FavoritesComponent {
    val uiState: Value<FavoritesUIState>
    fun onCharClicked(data: CharacterEntity)
}