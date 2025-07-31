package org.example.kmpskeleton.domain.usecase

import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity
import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class InsertFavoriteUseCase (
    private val characterRepo: ICharacterRepo
) {
    suspend operator fun invoke(characterEntity: CharacterEntity){
        characterRepo.insertCharacter(characterEntity)
    }

}