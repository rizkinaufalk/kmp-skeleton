package org.example.kmpskeleton.domain.usecase

import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class GetAllFavCharacterUseCase (
    private val characterRepo: ICharacterRepo
) {
     operator fun invoke() {
        characterRepo.getAllFavCharacter()
    }
}