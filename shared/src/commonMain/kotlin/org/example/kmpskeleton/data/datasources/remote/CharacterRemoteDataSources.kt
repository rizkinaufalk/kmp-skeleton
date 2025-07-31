package org.example.kmpskeleton.data.datasources.remote

import org.example.kmpskeleton.data.datasources.remote.model.CharacterDto
import org.example.kmpskeleton.data.datasources.remote.model.CharacterPageDto

interface CharacterRemoteDataSources {
    suspend fun getCharacterPage(page: Int): CharacterPageDto
    suspend fun getCharacterById(id: Int): CharacterDto
}