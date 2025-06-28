package org.example.kmpskeleton.data.datasources

import org.example.kmpskeleton.data.remote.CharacterResponse
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.NetworkResponse
import org.example.kmpskeleton.domain.datasources.ICharacterDataSources
import org.example.kmpskeleton.domain.remote.ICharacterApi

class CharacterDataSourcesImpl(
    private val remote: ICharacterApi
): ICharacterDataSources {
    override suspend fun getCharacter(page: Int): NetworkResponse<CharacterResponse> {
        return remote.getCharacter(page)
    }

    override suspend fun getCharacterById(id: Int): NetworkResponse<CharacterEntity> {
        return remote.getCharacterById(id)
    }
}