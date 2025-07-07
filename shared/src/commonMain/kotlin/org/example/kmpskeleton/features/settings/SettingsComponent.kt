package org.example.kmpskeleton.features.settings

import com.arkivanov.decompose.value.Value

interface SettingsComponent {
    val settingsUIState: Value<SettingsUIState>
    fun onDeleteAllFavClicked()
}