package org.example.kmpskeleton.data.datasources.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val name: String? = null,
    val url: String? = null
)