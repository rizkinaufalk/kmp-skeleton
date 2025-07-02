package org.example.kmpskeleton.data.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class CharacterEntity(
	val gender: String? = null,
	val species: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val type: String? = null,
	val status: String? = null,
	val image: String? = null,
	val isFavourite: Boolean? = false,
)

