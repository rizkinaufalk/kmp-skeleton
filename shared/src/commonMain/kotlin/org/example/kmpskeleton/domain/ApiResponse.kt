package org.example.kmpskeleton.domain

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val message: String? = null,
    val data: T? = null
)
