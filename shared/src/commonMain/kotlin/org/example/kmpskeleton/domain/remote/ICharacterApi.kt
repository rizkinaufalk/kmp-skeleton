package org.example.kmpskeleton.domain.remote

import org.example.kmpskeleton.data.remote.CharacterResponse
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.data.remote.entity.YourRemoteEntity
import org.example.kmpskeleton.domain.NetworkResponse

interface ICharacterApi {
    suspend fun getCharacter(page: Int): NetworkResponse<CharacterResponse>
    suspend fun getCharacterById(id: Int): NetworkResponse<CharacterEntity>
}