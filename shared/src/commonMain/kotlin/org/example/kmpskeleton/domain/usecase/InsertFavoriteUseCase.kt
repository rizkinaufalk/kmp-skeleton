package org.example.kmpskeleton.domain.usecase

import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class InsertFavoriteUseCase (
    private val characterRepo: ICharacterRepo
) {
    suspend operator fun invoke(params: CharacterEntity) {
        characterRepo.insertCharacter(params)
    }

}