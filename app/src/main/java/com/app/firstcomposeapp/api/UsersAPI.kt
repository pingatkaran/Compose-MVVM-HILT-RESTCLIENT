package com.app.firstcomposeapp.api

import com.app.firstcomposeapp.models.Users
import retrofit2.Response
import retrofit2.http.GET

interface UsersAPI {

    @GET("users")
    suspend fun getUsers() : Response<Users>
}