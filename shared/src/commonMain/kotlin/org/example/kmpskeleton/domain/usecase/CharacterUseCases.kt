package org.example.kmpskeleton.domain.usecase

class CharacterUseCases (
    val getCharacterPage: GetCharacterPageUseCase,
    val getAllFavCharacter: GetAllFavCharacterUseCase,
    val insertFavorite: InsertFavoriteUseCase,
    val getCharacterByIdRemote: GetCharacterByIdRemoteUseCase,
    val getCharacterByIdLocal: GetCharacterByIdLocalUseCase,
    val isCharacterExist: IsCharacterExistUseCase,
    val deleteAllFavCharacter: DeleteAllFavCharacterUseCase,
    val removeFavCharacter: RemoveFavCharacterUseCase,
    val toggleFavCharacter: ToggleFavCharacterUseCase
)