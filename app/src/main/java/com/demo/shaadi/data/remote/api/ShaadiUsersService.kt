package com.demo.shaadi.data.remote.api

import com.demo.shaadi.data.model.ShaadiUsers
import retrofit2.http.GET
import retrofit2.http.Headers

interface ShaadiUsersService {

    @Headers("Accept: application/json")
    @GET("?results=10")
    suspend fun getShaadiUsers(): ShaadiUsers
}