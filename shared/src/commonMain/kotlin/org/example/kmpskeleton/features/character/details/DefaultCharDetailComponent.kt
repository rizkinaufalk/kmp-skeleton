package org.example.kmpskeleton.features.character.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity
import org.example.kmpskeleton.domain.model.Character
import org.example.kmpskeleton.domain.usecase.CharacterUseCases
import org.example.kmpskeleton.domain.usecase.IsCharacterExistUseCase
import org.example.kmpskeleton.domain.usecase.ToggleFavCharacterUseCase
import org.example.kmpskeleton.features.character.CharListUIState

class DefaultCharDetailComponent(
    componentContext: ComponentContext,
    characterId: Int,
    private val characterUseCases: CharacterUseCases,
    private val onFinished: () -> Unit
) : CharDetailComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableValue(CharDetailUIState())
    override val uiState: Value<CharDetailUIState> = _uiState

    init {
        scope.launch {
            val isFav = characterUseCases.isCharacterExist(characterId)
            _uiState.value = _uiState.value.copy(isFavourite = isFav)
        }
    }

    override fun onToggleFavorite() {
        scope.launch {
            val character = _uiState.value.character ?: return@launch
            characterUseCases.toggleFavCharacter(character)
            val isFav = characterUseCases.isCharacterExist(character.id ?: 0)
            _uiState.value = _uiState.value.copy(isFavourite = isFav)
        }
    }
    override fun onBackPressed() = onFinished()
}