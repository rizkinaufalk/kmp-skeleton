package org.example.kmpskeleton.features.character.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.data.repositories.CharacterRepoImpl
import org.example.kmpskeleton.domain.usecase.InsertFavoriteUseCase
import org.example.kmpskeleton.domain.usecase.IsFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.ToggleFavCharacterUseCase

class DefaultCharDetailComponent(
    componentContext: ComponentContext,
    character: CharacterEntity,
    private val toggleFavCharacterUseCase: ToggleFavCharacterUseCase,
    private val isFavCharacterUseCase: IsFavCharacterUseCase,
    private val onFinished: () -> Unit
) : CharDetailComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        scope.launch {
            val isFav = isFavCharacterUseCase(character.id ?: 0)
            _uiState.value = _uiState.value.copy(isFavorited = isFav)
        }
    }

    private val _uiState = MutableValue(
        CharDetailUIState(
            isLoading = false,
            character = character,
            errorMessage = null,
            isFavorited = false
        )
    )
    override val uiState: Value<CharDetailUIState> = _uiState

    override fun onToggleFavorite() {
        scope.launch {
            val character = _uiState.value.character ?: return@launch
            toggleFavCharacterUseCase(character)
            val isFav = isFavCharacterUseCase(character.id ?: 0)
            _uiState.value = _uiState.value.copy(isFavorited = isFav)
        }
    }
    override fun onBackPressed() = onFinished()
}