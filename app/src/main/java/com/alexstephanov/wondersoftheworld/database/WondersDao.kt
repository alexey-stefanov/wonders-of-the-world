package com.alexstephanov.wondersoftheworld.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexstephanov.wondersoftheworld.model.ListItemModel

@Dao
interface WondersDao {
    @Query("SELECT * FROM ancient_table")
    fun getAllItems() : List<ListItemModel>

    @Insert
    fun addItem(listItem: ListItemModel)

    @Insert
    fun addItems(listItemList: List<ListItemModel>)
}