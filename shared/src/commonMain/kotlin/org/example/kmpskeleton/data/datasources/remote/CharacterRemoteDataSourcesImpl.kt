package org.example.kmpskeleton.data.datasources.remote

import org.example.kmpskeleton.data.datasources.remote.model.CharacterDto
import org.example.kmpskeleton.data.datasources.remote.model.CharacterPageDto

class CharacterRemoteDataSourcesImpl(
    private val remote: CharacterApi
) : CharacterRemoteDataSources {
    override suspend fun getCharacterPage(page: Int): CharacterPageDto {
        return remote.getCharacterPage(page)
    }

    override suspend fun getCharacterById(id: Int): CharacterDto {
        return remote.getCharacterById(id)
    }
}