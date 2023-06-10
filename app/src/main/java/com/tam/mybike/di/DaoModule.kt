package com.tam.mybike.di

import com.tam.mybike.data.dao.BikeDao
import com.tam.mybike.data.dao.RideDao
import com.tam.mybike.data.database.MyBikeDatabase
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
    fun provideBikeDao(database: MyBikeDatabase): BikeDao =
        database.bikeDao

    @Provides
    @Singleton
    fun provideRideDao(database: MyBikeDatabase): RideDao =
        database.rideDao

}