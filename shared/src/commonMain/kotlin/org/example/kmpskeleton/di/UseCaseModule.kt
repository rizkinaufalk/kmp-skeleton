package org.example.kmpskeleton.di

import org.example.kmpskeleton.domain.usecase.GetCharacterByIdUseCase
import org.koin.dsl.module


val provideUseCaseModule = module {
 single <GetCharacterByIdUseCase> {
     GetCharacterByIdUseCase(characterRepo = get())
 }
}