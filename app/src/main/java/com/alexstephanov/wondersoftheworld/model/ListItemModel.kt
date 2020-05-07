package com.alexstephanov.wondersoftheworld.model

data class ListItemModel (
    val id: Int,
    val name: String,
    val description: String,
    val location: String,
    val type: String,
    val creationDate: String,
    val destructionDate: String,
    val latitude: Double,
    val longitude: Double,
    val url: String
)