package org.example.kmpskeleton.data.datasources.local

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity

interface CharacterLocalDataSources {
    suspend fun insertCharacter(character: CharacterEntity)

    suspend fun isCharacterExist(id: Int): Boolean

    fun getAllFavCharacter(): Flow<List<CharacterEntity>>

    suspend fun getCharacterById(id: Int): CharacterEntity

    suspend fun removeCharacter(id: Int)

    suspend fun deleteAllFavCharacter()
}