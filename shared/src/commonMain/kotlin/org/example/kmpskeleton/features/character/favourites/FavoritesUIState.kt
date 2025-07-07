package org.example.kmpskeleton.features.character.favourites

import org.example.kmpskeleton.data.remote.entity.CharacterEntity

data class FavoritesUIState(
    val characters: List<CharacterEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)