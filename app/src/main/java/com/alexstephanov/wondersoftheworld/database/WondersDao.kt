package com.alexstephanov.wondersoftheworld.database

import androidx.room.*
import com.alexstephanov.wondersoftheworld.model.AncientWondersItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersItemModel
import com.alexstephanov.wondersoftheworld.model.NatureWondersListItemModel

@Dao
interface WondersDao {
    @Query("SELECT * FROM ancient_wonders_table")
    fun getAllAncientWondersItems() : List<AncientWondersItemModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAncientsWondersItems(listItemList: List<AncientWondersItemModel>)

    @Query("DELETE FROM ancient_wonders_table")
    suspend fun clearAncientWondersTable()

    @Query("SELECT * FROM modern_wonders_table")
    fun getAllModernWondersItems() : List<ModernWondersItemModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addModernWondersItems(listItemList: List<ModernWondersItemModel>)

    @Query("DELETE FROM modern_wonders_table")
    suspend fun clearModernWondersTable()

    @Query("SELECT * FROM nature_wonders_table")
    fun getAllNatureWondersItems() : List<NatureWondersListItemModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNatureWondersItems(listItemList: List<NatureWondersListItemModel>)

    @Query("DELETE FROM nature_wonders_table")
    suspend fun clearNatureWondersTable()
}