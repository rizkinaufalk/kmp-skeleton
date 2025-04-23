package org.example.kmpskeleton.di

import org.example.kmpskeleton.data.remote.CharacterApiImpl
import org.example.kmpskeleton.domain.remote.ICharacterApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

val provideRemoteModule = module {
    single<ICharacterApi>{
        CharacterApiImpl(httpClient = get(named("app")))
    }
}