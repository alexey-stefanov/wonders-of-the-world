package com.alexstephanov.wondersoftheworld.model

interface ListItemModel {
    val id: Int
    val title: String
    val description: String
    val location: String
    val creationDate: String
    val destructionDate: String
    val latitude: Double
    val longitude: Double
    val url: String
}