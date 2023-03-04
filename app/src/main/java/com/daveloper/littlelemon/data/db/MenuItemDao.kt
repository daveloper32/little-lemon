package com.daveloper.littlelemon.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.daveloper.littlelemon.data.model.MenuItemEntity

@Dao
interface MenuItemDao {
    companion object {
        const val TABLE_NAME = "menu_table"
    }
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): LiveData<List<MenuItemEntity>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemEntity)

    @Query("SELECT (SELECT COUNT(*) FROM $TABLE_NAME) == 0")
    fun isEmpty(): Boolean

    @Query("DELETE FROM $TABLE_NAME")
    fun clearTable()
}