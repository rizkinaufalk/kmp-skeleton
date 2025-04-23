package org.example.kmpskeleton.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration = {}){
    startKoin {
        config()
        modules(
            provideNetworkModule,
            provideDatasourceModule,
            provideRepositoryModule,
            provideUseCaseModule,
            provideRemoteModule
        )
    }
}