package com.alexstephanov.wondersoftheworld.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ancient_table")
data class ListItemModel(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,
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
    val latitude: Double,
    @SerializedName("longitude")
    @Expose
    val longitude: Double,
    @SerializedName("url")
    @Expose
    val url: String
)