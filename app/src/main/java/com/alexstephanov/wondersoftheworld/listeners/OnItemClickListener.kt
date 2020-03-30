package com.alexstephanov.wondersoftheworld.listeners

interface OnItemClickListener<T> {
    fun onItemClick(itemModel: T)
}