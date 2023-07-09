package com.example.inventorytracking.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@Entity(tableName = "inventory")
data class Item(
    @PrimaryKey @ColumnInfo("name") val name: String,
    @ColumnInfo("quantity") val quantity: Int,
    @ColumnInfo("price") val price: Double
)
