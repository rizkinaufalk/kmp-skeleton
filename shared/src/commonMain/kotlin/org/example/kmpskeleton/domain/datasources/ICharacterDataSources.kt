package org.example.kmpskeleton.domain.datasources

import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.NetworkResponse

interface ICharacterDataSources {
    suspend fun getCharacterById(id: Int): NetworkResponse<CharacterEntity>
}