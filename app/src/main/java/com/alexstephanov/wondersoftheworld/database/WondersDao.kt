package com.alexstephanov.wondersoftheworld.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alexstephanov.wondersoftheworld.model.AncientWondersListItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersListItemModel

@Dao
interface WondersDao {
    @Query("SELECT * FROM ancient_wonders_table")
    fun getAllAncientWondersItems() : List<AncientWondersListItemModel>

    @Insert
    fun addAncientWondersItem(listItem: AncientWondersListItemModel)

    @Insert
    fun addAncientsWondersItems(listItemList: List<AncientWondersListItemModel>)

    @Query("SELECT * FROM modern_wonders_table")
    fun getAllModernWondersItems() : List<ModernWondersListItemModel>

    @Insert
    fun addModernWondersItem(listItem: ModernWondersListItemModel)

    @Insert
    fun addModernWondersItems(listItemList: List<ModernWondersListItemModel>)
}