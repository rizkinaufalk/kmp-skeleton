package org.example.kmpskeleton.ui.components.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class ToolbarConfig(
    val title: String? = null,
    val showBack: Boolean = false,
    val actions: @Composable RowScope.() -> Unit = {})
