package org.example.kmpskeleton.di

import android.content.Context
import org.example.kmpskeleton.data.datasources.local.DatabaseDriverFactory
import org.koin.dsl.module

fun androidModule(context: Context) = module {
    single{ DatabaseDriverFactory(context) }
}