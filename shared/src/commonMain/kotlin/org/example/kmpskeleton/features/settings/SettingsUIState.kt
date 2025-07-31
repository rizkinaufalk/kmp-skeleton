package org.example.kmpskeleton.features.settings

import org.example.kmpskeleton.domain.util.DataError

data class SettingsUIState(
    val isDeleting: Boolean = false,
    val errorMessage: DataError? = null
)