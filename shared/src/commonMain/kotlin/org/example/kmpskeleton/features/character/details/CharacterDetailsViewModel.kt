package org.example.kmpskeleton.features.character.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.subscribers.Resource
import org.example.kmpskeleton.domain.usecase.GetCharacterByIdUseCase
import org.example.kmpskeleton.features.UiState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class CharacterDetailsViewModel: ViewModel(), KoinComponent { // later try to use rickclephas for viewmodel library

    private val getCharacterByIdUseCase: GetCharacterByIdUseCase by inject()

    private val _uiState = MutableStateFlow<UiState<CharacterEntity?>>(UiState.Loading)
    val uiState: StateFlow<UiState<CharacterEntity?>> = _uiState

    private val viewmodelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun getCharacterById(id: Int){
        _uiState.value = UiState.Loading
        viewmodelScope.launch {
            getCharacterByIdUseCase.invoke(id).collect{ resource ->
                when (resource){
                    is Resource.Success -> {
                        _uiState.value = UiState.Success(resource.data)
                    }

                    is Resource.Failure ->{
                        _uiState.value = UiState.Error(resource.failureData.message)
                    }
                    Resource.Loading -> TODO()
                }

            }
        }
    }
}