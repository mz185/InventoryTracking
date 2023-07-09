package com.example.inventorytracking.di

import com.example.inventorytracking.data.room.InventoryDao
import com.example.inventorytracking.data.room.InventoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideItemDao(inventoryDatabase: InventoryDatabase): InventoryDao {
        return inventoryDatabase.inventoryDao()
    }
}
