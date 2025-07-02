package org.example.kmpskeleton.features.character

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.example.kmpskeleton.RickAppDB
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.subscribers.Resource
import org.example.kmpskeleton.domain.usecase.GetCharacterUseCase

class DefaultCharListComponent(
    componentContext: ComponentContext,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val charClicked: (CharacterEntity) -> Unit,
) : CharListComponent, ComponentContext by componentContext{

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableValue(CharListUIState())
    override val uiState: Value<CharListUIState> = _uiState

    private var isLoading = false
    private var currentPage = 1
    private var endReached = false

    init {
        loadCharacters()
    }

    private fun loadCharacters(page: Int = 1) {
        if (isLoading || endReached) return

        isLoading = true
        _uiState.value = _uiState.value.copy(isLoading = true)

        scope.launch {
            getCharacterUseCase(page).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val newItems = result.data?.characters.orEmpty()

                        // Update endReached flag if no more data
                        if (newItems.isEmpty()) {
                            endReached = true
                        }

                        // Accumulate characters
                        val updatedList = _uiState.value.characters + newItems
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            characters = updatedList,
                            errorMessage = null,
                            currentPage = page
                        )

                        currentPage++ // move to next page
                        isLoading = false
                    }

                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.failureData.message ?: "Unknown error"
                        )
                        isLoading = false
                    }

                    is Resource.Loading -> {
                        // Optional: you may keep this if you want fine-grained loading state
                    }

                    Resource.None -> {
                        isLoading = false
                    }
                }
            }
        }
    }

//    override val model: Value<List<CharacterEntity>> = MutableValue(
//        data
//    )

    override fun onCharClicked(data: CharacterEntity) = charClicked(data)

    override fun nextPage() {
        loadCharacters(currentPage++)
    }
}

