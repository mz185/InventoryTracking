package com.example.inventorytracking.data.room

import androidx.room.*
import com.example.inventorytracking.models.Item

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@Dao
interface InventoryDao {
    @Insert
    suspend fun insert(item: Item): Long

    @Query("SELECT * FROM inventory")
    suspend fun getAll(): List<Item>

    @Update
    suspend fun update(item: Item): Int

    @Query("SELECT * FROM inventory WHERE name = :itemName")
    fun getItemByName(itemName: String): Item

    @Delete
    fun delete(item: Item): Int

    @Query("SELECT SUM(quantity * price) FROM inventory")
    suspend fun calculateTotalValue(): Double
}
