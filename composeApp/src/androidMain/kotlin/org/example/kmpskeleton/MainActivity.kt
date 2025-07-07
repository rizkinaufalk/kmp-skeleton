package org.example.kmpskeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.DefaultComponentContext
import org.example.kmpskeleton.domain.usecase.GetCharacterUseCase
import org.example.kmpskeleton.navigation.DefaultRootComponent
import org.example.kmpskeleton.ui.components.toolbar.AppScaffold
import org.example.kmpskeleton.ui.components.toolbar.BottomNavTab
import org.example.kmpskeleton.ui.components.toolbar.ToolbarConfig
import org.example.kmpskeleton.ui.components.toolbar.tabs
import org.example.kmpskeleton.ui.navigation.RickApp
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            val rootComponent = remember{
                DefaultRootComponent(componentContext = DefaultComponentContext(lifecycle = lifecycle))
            }
            var selectedTab by rememberSaveable { mutableStateOf(BottomNavTab.HOME) }
            val toolbarConfig = remember { mutableStateOf(ToolbarConfig()) }
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    rootComponent.onBack()
                }
            })

            Surface (
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background){
                AppScaffold(
                    toolbarConfig = toolbarConfig.value,
                    selectedItemIndex = tabs.indexOf(selectedTab),
                    onBottomNavItemClick = {index ->
                        val selected = tabs[index]
                        selectedTab = selected
                        when (selected) {
                            BottomNavTab.HOME -> rootComponent.navigateToCharList()
                            BottomNavTab.FAVORITES -> rootComponent.navigateToFavorites()
                            BottomNavTab.SETTINGS -> rootComponent.navigateToSettings()
                        } },
                    onBack = { rootComponent.onBack() },
                    scrollBehavior = scrollBehavior
                ) {
                    RickApp(rootComponent, updateToolbar = { toolbarConfig.value = it })
                }
            }
        }
    }
}
