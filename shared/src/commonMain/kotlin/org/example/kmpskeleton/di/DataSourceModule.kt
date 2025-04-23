package org.example.kmpskeleton.di

import org.example.kmpskeleton.data.datasources.CharacterDataSourcesImpl
import org.example.kmpskeleton.domain.datasources.ICharacterDataSources
import org.koin.dsl.module


val provideDatasourceModule = module {
    single<ICharacterDataSources>{
        CharacterDataSourcesImpl(remote = get())
    }
}