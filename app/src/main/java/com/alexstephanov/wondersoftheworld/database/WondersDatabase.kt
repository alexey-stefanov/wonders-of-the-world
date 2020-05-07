package com.alexstephanov.wondersoftheworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexstephanov.wondersoftheworld.model.AncientWondersItemModel
import com.alexstephanov.wondersoftheworld.model.ModernWondersItemModel
import com.alexstephanov.wondersoftheworld.model.NatureWondersListItemModel

@Database(entities = [AncientWondersItemModel::class, ModernWondersItemModel::class, NatureWondersListItemModel::class], version = 1, exportSchema = false)
abstract class WondersDatabase : RoomDatabase() {
    abstract fun getWondersDao() : WondersDao

    companion object {

        private const val databaseName = "wonders_database.db"
        private val lock = Any()

        @Volatile
        private var INSTANCE: WondersDatabase? = null

        fun getInstance(context: Context): WondersDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(lock) {
                val instance = Room.databaseBuilder(context,
                    WondersDatabase::class.java, databaseName).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}