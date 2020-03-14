package com.alexstephanov.wondersoftheworld.listeners

import com.alexstephanov.wondersoftheworld.model.ListItem

interface OnItemClickListener {
    fun onItemClick(item: ListItem)
}