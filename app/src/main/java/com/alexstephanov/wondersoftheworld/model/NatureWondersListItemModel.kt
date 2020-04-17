package com.alexstephanov.wondersoftheworld.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "nature_wonders_table")
data class NatureWondersListItemModel(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("location")
    @Expose
    val location: String,
    @SerializedName("latitude")
    @Expose
    val latitude: Double,
    @SerializedName("longitude")
    @Expose
    val longitude: Double,
    @SerializedName("url")
    @Expose
    val url: String
)