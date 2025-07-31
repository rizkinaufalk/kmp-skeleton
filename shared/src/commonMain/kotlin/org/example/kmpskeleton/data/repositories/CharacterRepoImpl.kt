package org.example.kmpskeleton.data.repositories

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.datasources.local.CharacterLocalDataSources
import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity
import org.example.kmpskeleton.data.datasources.remote.CharacterRemoteDataSources
import org.example.kmpskeleton.data.datasources.remote.model.CharacterDto
import org.example.kmpskeleton.data.datasources.remote.model.CharacterPageDto
import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class CharacterRepoImpl(
    private val remote: CharacterRemoteDataSources,
    private val local: CharacterLocalDataSources
) : ICharacterRepo {

    override suspend fun getCharacterPage(page: Int): CharacterPageDto {
        return remote.getCharacterPage(page)
    }

    override suspend fun getCharacterByIdRemote(id: Int): CharacterDto {
        return remote.getCharacterById(id)
    }

    override suspend fun getCharacterByIdLocal(id: Int): CharacterEntity {
//        return local.getAllFavCharacter(id)
        return CharacterEntity()
    }

    override fun getAllFavCharacter(): Flow<List<CharacterEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCharacter(character: CharacterEntity) {
        local.insertCharacter(character)
    }

    override suspend fun isCharacterExist(id: Int): Boolean {
        return local.isCharacterExist(id)
    }

    override suspend fun removeCharacter(id: Int) {
        local.removeCharacter(id)
    }

    override suspend fun deleteAllFavCharacter() {
        local.deleteAllFavCharacter()
    }
}