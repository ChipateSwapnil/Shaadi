package com.demo.shaadi.di

import com.demo.shaadi.data.repositories.ShaadiUsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ShaadiUsersRepository(get(), get(), get()) }
}