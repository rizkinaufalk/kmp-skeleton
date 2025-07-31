package org.example.kmpskeleton.data.datasources.remote.model

import kotlinx.serialization.Serializable
import org.example.kmpskeleton.domain.model.Character

@Serializable
data class CharacterPageDto(
    val info: PageInfoDto? = null,
    val results: List<CharacterDto>? = null
)

fun CharacterPageDto.toDomain(): List<Character> {
    return results?.map { it.toDomain() } ?: emptyList()
}