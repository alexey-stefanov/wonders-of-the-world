package com.alexstephanov.wondersoftheworld.server_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private val retrofit: Retrofit
    private const val BASE_URL = "http://192.168.43.120/WondersAPI/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : JSONPlaceholderApi {
        return retrofit.create(JSONPlaceholderApi::class.java)
    }
}