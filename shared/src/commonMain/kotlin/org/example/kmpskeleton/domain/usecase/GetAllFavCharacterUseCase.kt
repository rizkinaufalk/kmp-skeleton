package org.example.kmpskeleton.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.subscribers.Resource

class GetAllFavCharacterUseCase (
    private val characterRepo: ICharacterRepo
) {
     operator fun invoke(): Flow<Resource<List<CharacterEntity>>> {
        return characterRepo.getAllFavCharacter()
    }
}