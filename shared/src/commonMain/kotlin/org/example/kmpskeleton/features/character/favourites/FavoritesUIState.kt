package org.example.kmpskeleton.features.character.favourites

import org.example.kmpskeleton.domain.model.Character
import org.example.kmpskeleton.domain.util.DataError

data class FavoritesUIState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: DataError? = null
)