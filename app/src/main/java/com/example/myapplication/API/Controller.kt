package com.example.myapplication.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Controller {
    companion object {
        fun getApiArguments(): RedmoappAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://redmobapps.website/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RedmoappAPI::class.java)
        }
    }
}