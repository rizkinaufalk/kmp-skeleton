package org.example.kmpskeleton.data.datasources.remote

import org.example.kmpskeleton.data.datasources.remote.model.CharacterDto
import org.example.kmpskeleton.data.datasources.remote.model.CharacterPageDto

interface CharacterApi {
    suspend fun getCharacterPage(page: Int): CharacterPageDto
    suspend fun getCharacterById(id: Int): CharacterDto
}