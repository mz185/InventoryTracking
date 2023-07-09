package com.example.inventorytracking.data.repos

import com.example.inventorytracking.data.room.InventoryDao
import com.example.inventorytracking.models.Item
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

class InventoryRepositoryImpl @Inject constructor(
    private val dao: InventoryDao
) : InventoryRepository {

    override suspend fun getInventory(): Result<List<Item>> = ioExec {
        dao.getAll()
    }

    override suspend fun addItem(item: Item): Result<Boolean> = ioExec {
        dao.insert(item) > 0
    }

    override suspend fun deleteItem(name: String): Result<Boolean> = ioExec {
        dao.delete(dao.getItemByName(name)) > 0
    }

    override suspend fun updateItem(name: String, newQuantity: Int): Result<Boolean> = ioExec {
        dao.update(dao.getItemByName(name).copy(quantity = newQuantity)) > 0
    }

    override suspend fun calculateTotalValue(): Result<Double?> = ioExec {
        dao.calculateTotalValue()
    }

    private suspend fun <T> ioExec(call: suspend () -> T): Result<T> = withContext(IO) {
        try {
            Result.success(call())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
