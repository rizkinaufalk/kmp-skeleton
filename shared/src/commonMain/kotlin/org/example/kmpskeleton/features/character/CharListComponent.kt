package org.example.kmpskeleton.features.character

import com.arkivanov.decompose.value.Value

interface CharListComponent {
    val uiState: Value<CharListUIState>
    fun onCharClicked(id: Int)
    fun onPullRefresh()
    fun nextPage()
}