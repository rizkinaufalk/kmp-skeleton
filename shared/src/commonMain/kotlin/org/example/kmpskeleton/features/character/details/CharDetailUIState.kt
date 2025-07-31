package org.example.kmpskeleton.features.character.details

import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity
import org.example.kmpskeleton.domain.model.Character
import org.example.kmpskeleton.domain.util.DataError

data class CharDetailUIState(
    val isLoading: Boolean = true,
    val character: CharacterEntity? = null,
    val isFavourite: Boolean = false,
    val errorMessage: DataError? = null
)
