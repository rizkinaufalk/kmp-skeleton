package org.example.kmpskeleton.domain.usecase

import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class IsFavCharacterUseCase(
    private val characterRepo: ICharacterRepo
) {
    suspend operator fun invoke(id: Int): Boolean {
        return characterRepo.isCharacterExist(id)
    }
}