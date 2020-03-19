package com.alexstephanov.wondersoftheworld.listeners

import com.alexstephanov.wondersoftheworld.model.ListItemModel

interface OnItemClickListener {
    fun onItemClick(itemModel: ListItemModel)
}