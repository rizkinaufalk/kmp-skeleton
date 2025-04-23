package org.example.kmpskeleton.di

import org.example.kmpskeleton.features.character.details.CharacterDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CharacterDetailsViewModel)
}