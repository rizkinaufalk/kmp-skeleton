package org.example.kmpskeleton.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.datasources.remote.model.toDomain
import org.example.kmpskeleton.domain.model.Character
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.util.DataError
import org.example.kmpskeleton.domain.util.Result
import org.example.kmpskeleton.domain.util.flowResult

class GetCharacterPageUseCase(
    private val characterRepo: ICharacterRepo
) {

    operator fun invoke(page: Int): Flow<Result<List<Character>, DataError>> = flowResult {
        characterRepo.getCharacterPage(page).results?.map { list -> list.toDomain() } ?: emptyList()
    }

}