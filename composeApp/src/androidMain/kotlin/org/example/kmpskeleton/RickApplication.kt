package org.example.kmpskeleton

import android.app.Application
import org.example.kmpskeleton.di.androidModule
import org.example.kmpskeleton.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@RickApplication)
            modules(androidModule(applicationContext))
        }
    }
}