package com.example.inventorytracking.di

import com.example.inventorytracking.data.repos.InventoryRepository
import com.example.inventorytracking.data.repos.InventoryRepositoryImpl
import com.example.inventorytracking.data.room.InventoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Marinos Zinonos on 08/07/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideInventoryRepository(inventoryDao: InventoryDao): InventoryRepository {
        return InventoryRepositoryImpl(inventoryDao)
    }
}
