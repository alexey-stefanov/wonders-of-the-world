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
    override val id: Int,
    @SerializedName("name")
    @Expose
    override val name: String,
    @SerializedName("description")
    @Expose
    override val description: String,
    @SerializedName("location")
    @Expose
    override val location: String,
    @SerializedName("type")
    @Expose
    override val type: String,
    @SerializedName("latitude")
    @Expose
    override val latitude: Double,
    @SerializedName("longitude")
    @Expose
    override val longitude: Double,
    @SerializedName("url")
    @Expose
    override val url: String
) : ItemModel2