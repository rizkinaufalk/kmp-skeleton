package org.example.kmpskeleton.features

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val exceptionMessage: String?) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}