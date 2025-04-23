package org.example.kmpskeleton.domain.util

import org.example.kmpskeleton.domain.RootError

sealed interface Result<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): Result<D, E>
    data class Error<out D, out E: RootError>(val error:E): Result<D, E>
}