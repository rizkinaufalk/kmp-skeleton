package org.example.kmpskeleton.di

import org.example.kmpskeleton.domain.usecase.GetCharacterByIdUseCase
import org.example.kmpskeleton.domain.usecase.GetCharacterUseCase
import org.koin.dsl.module

val provideUseCaseModule = module {
    single { GetCharacterByIdUseCase(characterRepo = get()) }
    single { GetCharacterUseCase(characterRepo = get()) }
}