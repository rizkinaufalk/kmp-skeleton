package org.example.kmpskeleton.features.character.favourites

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity
import org.example.kmpskeleton.domain.usecase.CharacterUseCases
import org.example.kmpskeleton.domain.usecase.GetAllFavCharacterUseCase
import org.example.kmpskeleton.domain.util.Result

class DefaultFavoritesComponent(
    componentContext: ComponentContext,
    private val characterUseCases: CharacterUseCases,
    private val charClicked: (id: Int) -> Unit
) : FavoritesComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableValue(FavoritesUIState())
    override val uiState: Value<FavoritesUIState> = _uiState

    override fun onCharClicked(id: Int)  = charClicked(id)

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
            characterUseCases.getAllFavCharacter().collect { result ->
                _uiState.update { currentState ->
                    when (result) {
                        is Result.Error -> currentState.copy(
                            isLoading = false,
                            errorMessage = result.error
                        )

                        Result.Loading -> currentState.copy(
                            isLoading = true,
                            errorMessage = null
                        )

                        is Result.Success -> currentState.copy(
                            characters = result.data,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                isLoading = result is Result.Loading
            }
        }
    }
}