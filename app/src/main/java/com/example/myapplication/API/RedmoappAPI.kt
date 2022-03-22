package com.example.myapplication.API

import retrofit2.Call
import retrofit2.http.GET

interface RedmoappAPI {
    @GET("/dzF9zD8V")
    fun getURLorNull(): Call<String?>
}