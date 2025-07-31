package org.example.kmpskeleton.di

import org.example.kmpskeleton.RickAppDB
import org.example.kmpskeleton.data.datasources.local.CharacterLocalDataSources
import org.example.kmpskeleton.data.datasources.local.CharacterLocalDataSourcesImpl
import org.example.kmpskeleton.data.datasources.remote.CharacterRemoteDataSourcesImpl
import org.example.kmpskeleton.data.datasources.local.DatabaseDriverFactory
import org.example.kmpskeleton.data.datasources.remote.CharacterRemoteDataSources
import org.koin.dsl.module


val provideDatasourceModule = module {
    single<CharacterRemoteDataSources>{ CharacterRemoteDataSourcesImpl(remote = get()) }

    single<CharacterLocalDataSources>{ CharacterLocalDataSourcesImpl(local = get()) }

    single { RickAppDB(driver = get<DatabaseDriverFactory>().createDriver()) }

    single { get<RickAppDB>().characterTableQueries }
}