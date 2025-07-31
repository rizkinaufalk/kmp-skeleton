package org.example.kmpskeleton.di

import org.example.kmpskeleton.domain.usecase.CharacterUseCases
import org.example.kmpskeleton.domain.usecase.DeleteAllFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.GetAllFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterByIdLocalUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterByIdRemoteUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterPageUseCase
import org.example.kmpskeleton.domain.usecase.InsertFavoriteUseCase
import org.example.kmpskeleton.domain.usecase.IsCharacterExistUseCase
import org.example.kmpskeleton.domain.usecase.RemoveFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.ToggleFavCharacterUseCase
import org.example.kmpskeleton.navigation.ScreenComponentFactory
import org.example.kmpskeleton.navigation.ScreenComponentFactoryImpl
import org.koin.dsl.module

val provideUseCaseModule = module {
    single {
        CharacterUseCases(
            getCharacterPage = GetCharacterPageUseCase(characterRepo = get()),
            getAllFavCharacter = GetAllFavCharacterUseCase(characterRepo = get()),
            insertFavorite = InsertFavoriteUseCase(characterRepo = get()),
            getCharacterByIdRemote = GetCharacterByIdRemoteUseCase(characterRepo = get()),
            getCharacterByIdLocal = GetCharacterByIdLocalUseCase(characterRepo = get()),
            isCharacterExist = IsCharacterExistUseCase(characterRepo = get()),
            deleteAllFavCharacter = DeleteAllFavCharacterUseCase(characterRepo = get()),
            removeFavCharacter = RemoveFavCharacterUseCase(characterRepo = get()),
            toggleFavCharacter = ToggleFavCharacterUseCase(characterRepo = get())
        )
    }
    single<ScreenComponentFactory> { ScreenComponentFactoryImpl() }
}