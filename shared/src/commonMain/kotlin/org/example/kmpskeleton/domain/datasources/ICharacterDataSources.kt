package org.example.kmpskeleton.domain.datasources

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.remote.CharacterResponse
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.NetworkResponse

interface ICharacterDataSources {
    suspend fun getCharacter(page: Int): NetworkResponse<CharacterResponse>
    suspend fun getCharacterById(id: Int): NetworkResponse<CharacterEntity>

    fun insertCharacter(id: Int, name: String, imageUrl: String)
    suspend fun isCharacterExist(id: Int): Boolean
    fun getAllFavCharacter(): Flow<List<CharacterEntity>>
    fun removeCharacter(id: Int)
}