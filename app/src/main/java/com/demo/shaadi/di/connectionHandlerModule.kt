package com.demo.shaadi.di

import com.demo.shaadi.data.repositories.ShaadiUsersRepository
import com.demo.shaadi.utils.ConnectionHandler
import org.koin.dsl.module

val connectionHandlerModule = module {
    single { ConnectionHandler(get()) }
}