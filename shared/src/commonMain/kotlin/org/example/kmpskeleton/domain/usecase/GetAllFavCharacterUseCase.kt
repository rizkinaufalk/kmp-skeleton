package org.example.kmpskeleton.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.kmpskeleton.data.datasources.local.entity.toDomain
import org.example.kmpskeleton.domain.model.Character
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.util.DataError
import org.example.kmpskeleton.domain.util.Result
import org.example.kmpskeleton.domain.util.RootError
import org.example.kmpskeleton.domain.util.wrapWithResult

class GetAllFavCharacterUseCase(
    private val characterRepo: ICharacterRepo
) {
    operator fun invoke(): Flow<Result<List<Character>, DataError>> =
        characterRepo.getAllFavCharacter()
            .map { list -> list.toDomain() }
            .wrapWithResult()
}