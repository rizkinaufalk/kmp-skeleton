package org.example.kmpskeleton.features.character

import com.arkivanov.decompose.value.Value
import org.example.kmpskeleton.data.remote.entity.CharacterEntity

interface CharListComponent {
    val uiState: Value<CharListUIState>
    fun onCharClicked(data: CharacterEntity)
    fun nextPage()
}