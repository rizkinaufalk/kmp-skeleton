package org.example.kmpskeleton.features.character.favourites

import com.arkivanov.decompose.value.Value

interface FavoritesComponent {
    val uiState: Value<FavoritesUIState>
    fun onCharClicked(id: Int)
}