package org.example.kmpskeleton.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.NetworkResponse
import org.example.kmpskeleton.domain.datasources.ICharacterDataSources
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.subscribers.DataSource
import org.example.kmpskeleton.domain.subscribers.FailureData
import org.example.kmpskeleton.domain.subscribers.Resource

class CharacterRepoImpl(
    private val remoteSource: ICharacterDataSources
): ICharacterRepo {

    override suspend fun getCharacterById(id: Int): Flow<Resource<CharacterEntity?>> = flow{
        when (val response = remoteSource.getCharacterById(id)){
            is NetworkResponse.Success -> emit(Resource.Success(data = response.data, DataSource.REMOTE))
            is NetworkResponse.Failure -> emit(Resource.Failure(FailureData(0, "error")))
            is NetworkResponse.Unauthorized -> emit(Resource.Failure(FailureData(401, "Unauthorized")))
        }
    }
}