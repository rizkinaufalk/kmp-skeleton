package org.example.kmpskeleton.domain.usecase

import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class RemoveFavCharacterUseCase (
    private val characterRepo: ICharacterRepo
) {
    suspend operator fun invoke(id: Int) {
        characterRepo.removeCharacter(id)
    }
}