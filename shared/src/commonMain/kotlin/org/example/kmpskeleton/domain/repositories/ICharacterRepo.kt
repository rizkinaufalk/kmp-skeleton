package org.example.kmpskeleton.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity
import org.example.kmpskeleton.data.datasources.remote.model.CharacterDto
import org.example.kmpskeleton.data.datasources.remote.model.CharacterPageDto

interface ICharacterRepo {
    suspend fun getCharacterPage(page: Int): CharacterPageDto
    suspend fun getCharacterByIdRemote(id: Int): CharacterDto
    suspend fun getCharacterByIdLocal(id: Int): CharacterEntity
    suspend fun insertCharacter(character: CharacterEntity)
    suspend fun removeCharacter(id: Int)
    suspend fun deleteAllFavCharacter()
    fun getAllFavCharacter(): Flow<List<CharacterEntity>>
    suspend fun isCharacterExist(id: Int): Boolean
}