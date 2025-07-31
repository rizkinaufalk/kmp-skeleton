package org.example.kmpskeleton.di

import org.example.kmpskeleton.data.datasources.remote.CharacterApiImpl
import org.example.kmpskeleton.data.datasources.remote.CharacterApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

val provideRemoteModule = module {
    single<CharacterApi>{
        CharacterApiImpl(httpClient = get(named("app")))
    }
}