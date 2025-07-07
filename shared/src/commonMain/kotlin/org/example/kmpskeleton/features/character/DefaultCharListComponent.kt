package org.example.kmpskeleton.features.character

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
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
        if (_uiState.value.characters.isEmpty()){
            loadCharacters()
        }
    }

    private fun loadCharacters(page: Int = currentPage) {
        if (isLoading || endReached) return

        isLoading = true

        scope.launch {
            getCharacterUseCase(page).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val newItems = result.data?.characters.orEmpty()

                        if (newItems.isEmpty()) {
                            endReached = true
                        }

                        val updatedList = _uiState.value.characters + newItems
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            characters = updatedList,
                            errorMessage = null,
                            currentPage = page
                        )

                        currentPage++
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
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    override fun onCharClicked(data: CharacterEntity) = charClicked(data)

    override fun nextPage() {
        loadCharacters(currentPage++)
    }

    override fun onPullRefresh() {
        scope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            getCharacterUseCase(currentPage).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val newItems = result.data?.characters.orEmpty()

                        if (newItems.isEmpty()) {
                            endReached = true
                        }

                        val updatedList = _uiState.value.characters + newItems
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            isRefreshing = false,
                            characters = updatedList,
                            errorMessage = null,
                            currentPage = currentPage
                        )
                    }

                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            isRefreshing = false,
                            errorMessage = result.failureData.message ?: "Unknown error"
                        )
                    }

                    else -> {}
                }
            }

        }
    }
}

