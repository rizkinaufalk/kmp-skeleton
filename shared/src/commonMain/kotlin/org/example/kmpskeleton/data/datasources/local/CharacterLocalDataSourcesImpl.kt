package org.example.kmpskeleton.data.datasources.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.kmpskeleton.RickAppDB
import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity

class CharacterLocalDataSourcesImpl(
    private val local: RickAppDB
) : CharacterLocalDataSources {

    override suspend fun insertCharacter(character: CharacterEntity) {
        local.characterTableQueries.insertCharacter(
            id = character.id?.toLong(),
            name = character.name.orEmpty(),
            imageUrl = character.imageUrl.orEmpty(),
            status = character.status.orEmpty(),
            species = character.species.orEmpty(),
            gender = character.gender.orEmpty()
        )
    }

    override suspend fun getCharacterById(id: Int): CharacterEntity {
//        return local.characterTableQueries(id)
        return CharacterEntity()
    }

    override fun getAllFavCharacter(): Flow<List<CharacterEntity>> = flow {
        local.characterTableQueries.getAllFavourite()

    }

    override suspend fun isCharacterExist(id: Int): Boolean {
        return local.characterTableQueries.isCharacterExists(id.toLong()).executeAsOne()
    }

    override suspend fun removeCharacter(id: Int) {
        local.characterTableQueries.removeCharacter(id.toLong())
    }

    override suspend fun deleteAllFavCharacter() {
        local.characterTableQueries.deleteAllCharacters()
    }
}