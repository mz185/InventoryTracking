package com.example.inventorytracking.di

import android.app.Application
import androidx.room.Room
import com.example.inventorytracking.data.room.InventoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideInventoryDatabase(application: Application): InventoryDatabase {
        return Room.databaseBuilder(
            application,
            InventoryDatabase::class.java,
            "inventory_database"
        ).build()
    }
}
