package org.example.kmpskeleton.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)