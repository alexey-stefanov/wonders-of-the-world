package com.alexstephanov.wondersoftheworld.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexstephanov.wondersoftheworld.model.ListItemModel

@Database(entities = [ListItemModel::class], version = 1)
abstract class WondersDatabase : RoomDatabase() {
    abstract fun getWondersDao() : WondersDao
}