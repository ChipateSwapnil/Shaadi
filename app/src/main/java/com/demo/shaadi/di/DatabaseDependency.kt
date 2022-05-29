package com.demo.shaadi.di

import com.demo.shaadi.data.local.databse.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().photoDao() }
}