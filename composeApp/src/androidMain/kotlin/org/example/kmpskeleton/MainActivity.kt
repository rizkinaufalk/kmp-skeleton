package org.example.kmpskeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import org.example.kmpskeleton.data.repositories.CharacterRepoImpl
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.usecase.GetCharacterUseCase
import org.example.kmpskeleton.navigation.DefaultRootComponent
import org.example.kmpskeleton.ui.components.toolbar.AppToolbar
import org.example.kmpskeleton.ui.components.toolbar.ToolbarConfig
import org.example.kmpskeleton.ui.navigation.RickApp
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val getCharacterUseCase: GetCharacterUseCase = get()

        setContent {

            val rootComponent = remember{
                DefaultRootComponent(
                        componentContext = DefaultComponentContext(lifecycle = lifecycle),
                    getCharacterUseCase = getCharacterUseCase // <- from shared module
                )
            }

            // Shared toolbar config state
            val toolbarConfig = remember { mutableStateOf(ToolbarConfig()) }
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

            Surface (
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background){
                AppToolbar(
                    toolbarConfig = toolbarConfig.value,
                    onBack = { rootComponent.onBack() },
                    scrollBehavior
                ) {
                    RickApp(rootComponent, updateToolbar = { toolbarConfig.value = it })
                }
            }
        }
    }
}
