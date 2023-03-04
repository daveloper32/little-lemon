package com.daveloper.littlelemon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daveloper.littlelemon.data.model.MenuItemEntity

@Database(
    entities = [MenuItemEntity::class],
    version = 1,
    exportSchema = false
)

abstract class LittleLemonDatabase: RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}