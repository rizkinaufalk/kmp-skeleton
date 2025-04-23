package org.example.kmpskeleton.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.subscribers.Resource

interface ICharacterRepo{
    suspend fun getCharacterById(id: Int): Flow<Resource<CharacterEntity?>>
}