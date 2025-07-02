package org.example.kmpskeleton.di

import org.example.kmpskeleton.domain.usecase.GetAllFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterByIdUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterUseCase
import org.example.kmpskeleton.domain.usecase.InsertFavoriteUseCase
import org.example.kmpskeleton.domain.usecase.IsFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.RemoveFavCharacterUseCase
import org.example.kmpskeleton.domain.usecase.ToggleFavCharacterUseCase
import org.example.kmpskeleton.navigation.ScreenComponentFactory
import org.example.kmpskeleton.navigation.ScreenComponentFactoryImpl
import org.koin.dsl.module

val provideUseCaseModule = module {
    single { GetCharacterByIdUseCase(characterRepo = get()) }
    single { GetCharacterUseCase(characterRepo = get()) }
    single { InsertFavoriteUseCase(characterRepo = get()) }
    single { GetAllFavCharacterUseCase(characterRepo = get()) }
    single { ToggleFavCharacterUseCase(characterRepo = get()) }
    single { IsFavCharacterUseCase(characterRepo = get()) }
    single { RemoveFavCharacterUseCase(characterRepo = get()) }
    single<ScreenComponentFactory> { ScreenComponentFactoryImpl() }
}