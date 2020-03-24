package com.alexstephanov.wondersoftheworld.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListModel(
    @SerializedName("ancient_wonders")
    @Expose
    val ancientWonders: List<ListItemModel>,
    @SerializedName("modern_wonders")
    @Expose
    val modernWonders: List<ListItemModel>
)