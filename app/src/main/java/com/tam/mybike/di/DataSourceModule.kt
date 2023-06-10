package com.tam.mybike.di

import com.tam.mybike.data.source.DatabaseSource
import com.tam.mybike.data.source.RoomDataSource
import com.tam.mybike.data.source.SettingsDataSource
import com.tam.mybike.data.source.SharedPrefsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindDatabaseSource(roomDataSource: RoomDataSource): DatabaseSource

    @Binds
    @Singleton
    abstract fun bindSettingsDataSource(sharedPrefsDataSource: SharedPrefsDataSource): SettingsDataSource

}