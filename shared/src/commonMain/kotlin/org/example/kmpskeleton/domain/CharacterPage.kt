package org.example.kmpskeleton.domain

import org.example.kmpskeleton.data.remote.CharacterResponse
import org.example.kmpskeleton.data.remote.entity.CharacterEntity

data class CharacterPage(
    val nextPage: String?,
    val characters: List<CharacterEntity>
)

fun CharacterResponse.toDomain(): CharacterPage {
    return CharacterPage(
        nextPage = pageInfo.next,
        characters = characters
    )
}
