package org.example.kmpskeleton.domain.usecase

import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.subscribers.FailureData
import org.example.kmpskeleton.domain.subscribers.Resource

class GetCharacterByIdUseCase(
    private val characterRepo: ICharacterRepo
) {

    suspend operator fun invoke(params: Int): Flow<Resource<CharacterEntity?>> {
        return characterRepo.getCharacterById(params)
    }

}