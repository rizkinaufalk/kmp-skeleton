package org.example.kmpskeleton.features.settings

data class SettingsUIState(
    val isDeleting: Boolean = false,
    val errorMessage: String? = null,
)