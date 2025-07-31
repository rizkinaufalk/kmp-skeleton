package org.example.kmpskeleton.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.example.kmpskeleton.data.datasources.remote.model.toDomain
import org.example.kmpskeleton.domain.model.Character
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.example.kmpskeleton.domain.util.Result
import org.example.kmpskeleton.domain.util.RootError
import org.example.kmpskeleton.domain.util.flowResult

class GetCharacterByIdRemoteUseCase(
    private val characterRepo: ICharacterRepo
) {
    operator fun invoke(id: Int, ): Flow<Result<Character, RootError>> = flowResult {
        characterRepo.getCharacterByIdRemote(id).toDomain()
    }

}