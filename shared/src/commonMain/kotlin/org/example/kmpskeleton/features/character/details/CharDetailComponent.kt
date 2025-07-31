package org.example.kmpskeleton.features.character.details

import com.arkivanov.decompose.value.Value

interface CharDetailComponent {
    val uiState: Value<CharDetailUIState>
    fun onToggleFavorite()
    fun onBackPressed()
}