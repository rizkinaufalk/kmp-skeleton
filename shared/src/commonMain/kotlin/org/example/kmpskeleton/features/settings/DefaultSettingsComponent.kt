package org.example.kmpskeleton.features.settings

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.kmpskeleton.domain.usecase.CharacterUseCases
import org.example.kmpskeleton.domain.usecase.DeleteAllFavCharacterUseCase
import org.example.kmpskeleton.domain.util.DataError

class DefaultSettingsComponent(
    componentContext: ComponentContext,
    private val characterUseCases: CharacterUseCases,
) : SettingsComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val _uiState = MutableValue(SettingsUIState())
    override val settingsUIState: Value<SettingsUIState> = _uiState

    override fun onDeleteAllFavClicked() {
        scope.launch {
            _uiState.update { it.copy(isDeleting = true) }

            runCatching {
                characterUseCases.deleteAllFavCharacter()
            }.onSuccess {
                _uiState.update {
                    it.copy(
                        isDeleting = false,
                    )
                }
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isDeleting = false,
                        errorMessage = DataError.Local.FILE_NOT_FOUND
                    )
                }
            }
        }
    }
}