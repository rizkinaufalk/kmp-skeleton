package org.example.kmpskeleton.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YourRemoteEntity(
	@SerialName("first_name")
	val firstName: String? = null,
	val lastName: String? = null,
	val gender: String? = null,
	val maidenName: String? = null,
	val id: Int? = null,
	val age: Int? = null
)
