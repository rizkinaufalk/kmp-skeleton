package org.example.kmpskeleton.di

import org.example.kmpskeleton.data.repositories.CharacterRepoImpl
import org.example.kmpskeleton.domain.repositories.ICharacterRepo
import org.koin.dsl.module


val provideRepositoryModule = module {
  single<ICharacterRepo>{
      CharacterRepoImpl(dataSources = get())
  }
}