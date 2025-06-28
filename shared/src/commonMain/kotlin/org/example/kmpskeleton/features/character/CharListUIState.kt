package org.example.kmpskeleton.features.character

import org.example.kmpskeleton.data.remote.entity.CharacterEntity

data class CharListUIState(
    val characters: List<CharacterEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentPage: Int = 1,
    val endReached: Boolean = false
)
