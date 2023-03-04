package com.daveloper.littlelemon.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daveloper.littlelemon.data.db.MenuItemDao

@Entity(
    tableName = MenuItemDao.TABLE_NAME
)
class MenuItemEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
)
