package org.example.kmpskeleton.data.datasources.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PageInfoDto(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: String? = null
)