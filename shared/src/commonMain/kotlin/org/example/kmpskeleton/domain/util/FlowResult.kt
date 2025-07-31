package org.example.kmpskeleton.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

inline fun <T> flowResult(
    crossinline block: suspend () -> T
): Flow<Result<T, DataError>> = flow {
    emit(Result.Loading)
    try {
        emit(Result.Success(block()))
    } catch (e: Throwable) {
        emit(Result.Error(e.toDataError()))
    }
}

fun <T> Flow<T>.wrapWithResult(): Flow<Result<T, DataError>> =
    this
        .map<T, Result<T, DataError>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it.toDataError())) }