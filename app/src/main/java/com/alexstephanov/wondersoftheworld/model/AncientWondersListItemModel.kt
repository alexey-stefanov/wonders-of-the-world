package com.alexstephanov.wondersoftheworld.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ancient_wonders_table")
data class AncientWondersListItemModel(
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
    @SerializedName("date_cre")
    @Expose
    override val creationDate: String,
    @SerializedName("date_des")
    @Expose
    override val destructionDate: String,
    @SerializedName("latitude")
    @Expose
    override val latitude: Double,
    @SerializedName("longitude")
    @Expose
    override val longitude: Double,
    @SerializedName("url")
    @Expose
    override val url: String
) : ListItemModel