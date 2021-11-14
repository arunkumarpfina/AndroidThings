package com.arun.androidThings.data

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("api/users?page=2")
    fun getThings(): Call<Things>
}