package com.alexstephanov.wondersoftheworld.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel (
    @SerializedName("wonders")
    @Expose
    val listModel: ListModel
)