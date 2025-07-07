package org.example.kmpskeleton.features.character.favourites

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.example.kmpskeleton.Character
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.subscribers.Resource
import org.example.kmpskeleton.domain.usecase.GetAllFavCharacterUseCase
import org.example.kmpskeleton.features.character.CharListUIState

class DefaultFavoritesComponent(
    componentContext: ComponentContext,
    private val getAllFavCharacterUseCase: GetAllFavCharacterUseCase,
    private val charClicked: (CharacterEntity) -> Unit
) : FavoritesComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableValue(FavoritesUIState())
    override val uiState: Value<FavoritesUIState> = _uiState

    private var isLoading = false
    private var currentPage = 1
    private var endReached = false

    init {
        loadCharacter()
    }

    private fun loadCharacter() {
        if (isLoading) return

        isLoading = true
        _uiState.value = _uiState.value.copy(isLoading = true)

        scope.launch {
            getAllFavCharacterUseCase().collect { result ->
                _uiState.update { currentState ->
                    when (result) {
                        is Resource.Failure -> currentState.copy(
                            isLoading = false,
                            errorMessage = result.failureData.message
                        )
                        Resource.Loading -> currentState.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                        is Resource.Success -> currentState.copy(
                            characters = result.data.orEmpty(),
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                isLoading = result is Resource.Loading
            }
        }
    }

    override fun onCharClicked(data: CharacterEntity) = charClicked(data)
}