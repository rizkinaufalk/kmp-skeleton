package org.example.kmpskeleton.ui.components.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    toolbarConfig: ToolbarConfig,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    selectedItemIndex: Int,
    onBottomNavItemClick: (Int) -> Unit,
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
        },
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { onBottomNavItemClick(index) },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex)
                                    item.selectedIcon
                                else
                                    item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { values ->
        Box(modifier = Modifier.padding(values)) {
            content()
        }
    }
}