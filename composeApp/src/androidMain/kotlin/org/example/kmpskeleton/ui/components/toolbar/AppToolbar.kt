package org.example.kmpskeleton.ui.components.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    toolbarConfig: ToolbarConfig,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable () -> Unit,
){
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            toolbarConfig.title?.let {
                CenterAlignedTopAppBar(
                    title = { Text(it) },
                    navigationIcon = {
                        if (toolbarConfig.showBack) {
                            IconButton(onClick = onBack) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                            }
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    actions = toolbarConfig.actions
                )
            }
        }
    ) { values ->
        Box(modifier = Modifier.padding(values)) {
            content()
        }
    }
}