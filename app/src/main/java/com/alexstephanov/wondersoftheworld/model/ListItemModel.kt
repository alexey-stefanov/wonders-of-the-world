package com.alexstephanov.wondersoftheworld.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListItemModel(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("location")
    @Expose
    val location: String,
    @SerializedName("date_cre")
    @Expose
    val creationDate: String,
    @SerializedName("date_des")
    @Expose
    val destructionDate: String,
    @SerializedName("latitude")
    @Expose
    val latitude: String,
    @SerializedName("longitude")
    @Expose
    val longitude: String,
    @SerializedName("url")
    @Expose
    val url: String
)