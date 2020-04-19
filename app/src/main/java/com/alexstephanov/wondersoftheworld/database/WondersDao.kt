package com.alexstephanov.wondersoftheworld.database

import androidx.room.*
import com.alexstephanov.wondersoftheworld.model.AncientWondersListItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersListItemModel
import com.alexstephanov.wondersoftheworld.model.NatureWondersListItemModel

@Dao
interface WondersDao {
    @Query("SELECT * FROM ancient_wonders_table")
    fun getAllAncientWondersItems() : List<AncientWondersListItemModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAncientsWondersItems(listItemList: List<AncientWondersListItemModel>)

    @Query("DELETE FROM ancient_wonders_table")
    suspend fun clearAncientWondersTable()

    @Query("SELECT * FROM modern_wonders_table")
    fun getAllModernWondersItems() : List<ModernWondersListItemModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addModernWondersItems(listItemList: List<ModernWondersListItemModel>)

    @Query("DELETE FROM modern_wonders_table")
    suspend fun clearModernWondersTable()

    @Query("SELECT * FROM nature_wonders_table")
    fun getAllNatureWondersItems() : List<NatureWondersListItemModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNatureWondersItems(listItemList: List<NatureWondersListItemModel>)

    @Query("DELETE FROM nature_wonders_table")
    suspend fun clearNatureWondersTable()
}