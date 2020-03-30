package com.alexstephanov.wondersoftheworld.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexstephanov.wondersoftheworld.model.AncientWondersListItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersListItemModel

@Database(entities = [AncientWondersListItemModel::class, ModernWondersListItemModel::class], version = 1)
abstract class WondersDatabase : RoomDatabase() {
    abstract fun getWondersDao() : WondersDao
}