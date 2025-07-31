package org.example.kmpskeleton.features.character

import org.example.kmpskeleton.domain.model.Character
import org.example.kmpskeleton.domain.util.DataError

data class CharListUIState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: DataError? = null,
    val currentPage: Int = 1,
    val endReached: Boolean = false,
    val isRefreshing: Boolean = false
)
