package com.example.inventorytracking.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventorytracking.models.Item

@Database(entities = [Item::class], version = 1)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}
