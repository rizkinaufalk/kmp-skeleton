package org.example.kmpskeleton.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.kmpskeleton.data.remote.entity.CharacterEntity

@Serializable
data class CharacterResponse(
    @SerialName("info")
    val pageInfo: PageInfo,

    @SerialName("results")
    val characters: List<CharacterEntity>
)
