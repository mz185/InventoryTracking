package com.example.inventorytracking.data.repos

import com.example.inventorytracking.models.Item

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

interface InventoryRepository {
    suspend fun getInventory(): Result<List<Item>>
    suspend fun addItem(item: Item): Result<Boolean>
    suspend fun deleteItem(name: String): Result<Boolean>
    suspend fun updateItem(name: String, newQuantity: Int): Result<Boolean>
    suspend fun calculateTotalValue(): Result<Double?>
}
