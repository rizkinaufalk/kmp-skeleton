package org.example.kmpskeleton.data.datasources.local.entity

import kotlinx.serialization.Serializable
import org.example.kmpskeleton.domain.model.Character

@Serializable
data class CharacterEntity(
	val gender: String? = null,
	val species: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val type: String? = null,
	val status: String? = null,
	val imageUrl: String? = null,
	val isFavourite: Boolean? = false,
)


fun CharacterEntity.toDomain(): Character {
	return Character(
		gender = gender,
		species = species,
		name = name,
		id = id,
		type = type,
		status = status,
		imageUrl = imageUrl
	)
}

fun List<CharacterEntity>.toDomain(): List<Character> = map { it.toDomain() }

