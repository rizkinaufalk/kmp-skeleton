package org.example.kmpskeleton.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.remote.CharacterResponse
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.CharacterPage
import org.example.kmpskeleton.domain.subscribers.Resource

interface ICharacterRepo{
    fun getCharacter(page: Int): Flow<Resource<CharacterPage>>
    suspend fun getCharacterById(id: Int): Flow<Resource<CharacterEntity?>>
    suspend fun insertCharacter(character: CharacterEntity)
    suspend fun removeCharacter(id: Int)
    suspend fun deleteAllFavCharacter()
    fun getAllFavCharacter(): Flow<Resource<List<CharacterEntity>>>
    suspend fun isCharacterExist(id: Int): Boolean
}