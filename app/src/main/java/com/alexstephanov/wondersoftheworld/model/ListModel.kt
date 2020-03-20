package com.alexstephanov.wondersoftheworld.model

data class ListModel(
    val ancient_wonders: List<ListItemModel>,
    val modern_wonders: List<ListItemModel>
)