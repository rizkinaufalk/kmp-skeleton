package org.example.kmpskeleton.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.remote.CharacterResponse
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.CharacterPage
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.subscribers.Resource

class GetCharacterUseCase (
    private val characterRepo: ICharacterRepo
) {

    operator fun invoke(page: Int): Flow<Resource<CharacterPage>> {
        return characterRepo.getCharacter(page)
    }

}