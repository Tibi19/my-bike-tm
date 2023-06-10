package com.tam.mybike.di

import android.app.Application
import androidx.room.Room
import com.tam.mybike.data.database.DATABASE_NAME
import com.tam.mybike.data.database.MyBikeDatabase
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
    fun provideDatabase(application: Application): MyBikeDatabase =
        Room
            .databaseBuilder(
                context = application,
                klass = MyBikeDatabase::class.java,
                name = DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

}