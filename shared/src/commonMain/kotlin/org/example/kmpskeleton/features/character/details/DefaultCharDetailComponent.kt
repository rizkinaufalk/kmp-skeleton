package org.example.kmpskeleton.features.character.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import org.example.kmpskeleton.data.remote.entity.CharacterEntity

class DefaultCharDetailComponent(
    componentContext: ComponentContext,
    character: CharacterEntity,
    private val onFinished: () -> Unit
) : CharDetailComponent, ComponentContext by componentContext {


    private val _uiState = MutableValue(
        CharDetailUIState(
            isLoading = false,
            character = character,
            errorMessage = null
        )
    )
    override val uiState: Value<CharDetailUIState> = _uiState

    override fun onBackPressed() = onFinished()
}