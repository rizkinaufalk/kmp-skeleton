package org.example.kmpskeleton.data.datasources.remote.model

import kotlinx.serialization.Serializable
import org.example.kmpskeleton.domain.model.Character

@Serializable
data class CharacterDto(
    val created: String? = null,
    val episode: List<String>? = null,
    val gender: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val locationDto: LocationDto? = null,
    val name: String? = null,
    val originDto: OriginDto? = null,
    val species: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String? = null
)

fun CharacterDto.toDomain(): Character {
    return Character(
        gender = gender,
        species = species,
        name = name,
        id = id,
        type = type,
        status = status,
        imageUrl = image
    )
}