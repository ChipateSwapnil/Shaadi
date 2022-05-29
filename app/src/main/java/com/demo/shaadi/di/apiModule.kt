package com.demo.shaadi.di

import com.demo.shaadi.data.remote.api.ShaadiUsersService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(ShaadiUsersService::class.java) }
}