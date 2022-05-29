package com.demo.shaadi

import android.app.Application
import com.demo.shaadi.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        instance = this
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApp)
            modules(provideModules())
        }
    }

    private fun provideModules() =
        listOf(retrofitModule, apiModule, repositoryModule, viewModelModule, connectionHandlerModule, databaseModule)
}