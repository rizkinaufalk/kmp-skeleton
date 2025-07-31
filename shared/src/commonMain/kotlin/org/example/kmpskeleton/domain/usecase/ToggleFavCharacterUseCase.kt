package org.example.kmpskeleton.domain.usecase

import org.example.kmpskeleton.data.datasources.local.entity.CharacterEntity
import org.example.kmpskeleton.domain.repositories.ICharacterRepo

class ToggleFavCharacterUseCase (
    private val characterRepo: ICharacterRepo
){
    suspend operator fun invoke(character: CharacterEntity) {
        val isExist = characterRepo.isCharacterExist(character.id ?: 0)

        if (isExist){
            characterRepo.removeCharacter(character.id ?: 0)
        } else {
            characterRepo.insertCharacter(character)
        }
    }
}