package org.example.kmpskeleton.domain.usecase

import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class DeleteAllFavCharacterUseCase (
    private val characterRepo: ICharacterRepo
) {
    suspend operator fun invoke() {
        characterRepo.deleteAllFavCharacter()
    }
}