package com.test.plantix.di

import com.test.plantix.data.name.MockProvider
import com.test.plantix.data.name.NameLocalDBInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DataModule {

    @Provides
    @Singleton
    fun getLocalDatabaseInstance() = NameLocalDBInstance()

    @Provides
    @Singleton
    fun getMockDataInstance() = MockProvider()

}