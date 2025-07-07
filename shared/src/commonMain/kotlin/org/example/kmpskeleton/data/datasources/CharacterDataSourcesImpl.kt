package org.example.kmpskeleton.data.datasources

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.kmpskeleton.RickAppDB
import org.example.kmpskeleton.data.remote.CharacterResponse
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.NetworkResponse
import org.example.kmpskeleton.domain.datasources.ICharacterDataSources
import org.example.kmpskeleton.domain.remote.ICharacterApi

class CharacterDataSourcesImpl(
    private val remote: ICharacterApi,
    private val localDB: RickAppDB
) : ICharacterDataSources {
    override suspend fun getCharacter(page: Int): NetworkResponse<CharacterResponse> {
        return remote.getCharacter(page)
    }

    override suspend fun getCharacterById(id: Int): NetworkResponse<CharacterEntity> {
        return remote.getCharacterById(id)
    }

    override fun insertCharacter(
        id: Int,
        name: String,
        imageUrl: String,
        status: String,
        gender: String,
        species: String
    ) {
        localDB.characterTableQueries.insertCharacter(
            id = id.toLong(),
            name = name,
            imageUrl = imageUrl,
            status = status,
            species = species,
            gender = gender
        )
    }

    override fun getAllFavCharacter(): Flow<List<CharacterEntity>> {
        return localDB.characterTableQueries
            .getAllFavourite()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { list ->
                list.map { data ->
                    CharacterEntity(
                        id = data.id.toInt(),
                        name = data.name,
                        image = data.imageUrl,
                        status = data.status,
                        gender = data.gender,
                        species = data.species
                    )
                }
            }
    }

    override suspend fun isCharacterExist(id: Int): Boolean {
        return localDB.characterTableQueries.isCharacterExists(id.toLong()).executeAsOne()
    }

    override fun removeCharacter(id: Int) {
        localDB.characterTableQueries.removeCharacter(id.toLong())
    }

    override fun deleteAllFavCharacter() {
        localDB.characterTableQueries.deleteAllCharacters()
    }
}