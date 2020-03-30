package com.alexstephanov.wondersoftheworld.database

import android.content.Context
import androidx.room.Room

object DatabaseService {
    val database: WondersDatabase
    lateinit var context: Context

    init {
        database = Room.databaseBuilder(context, WondersDatabase::class.java, "wonders_database")
            .build()
    }
}