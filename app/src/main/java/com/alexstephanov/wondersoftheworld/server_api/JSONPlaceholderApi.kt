package com.alexstephanov.wondersoftheworld.server_api

import com.alexstephanov.wondersoftheworld.model.ListItemModel
import retrofit2.Call
import retrofit2.http.GET

interface JSONPlaceholderApi {

    @GET("data.php")
    fun getCurrentData() : Call<List<ListItemModel>>
}