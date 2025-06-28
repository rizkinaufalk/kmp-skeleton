package org.example.kmpskeleton.features.character.details

import org.example.kmpskeleton.data.remote.entity.CharacterEntity

data class CharDetailUIState(
    val isLoading: Boolean = true,
    val character: CharacterEntity? = null,
    val errorMessage: String? = null
)
