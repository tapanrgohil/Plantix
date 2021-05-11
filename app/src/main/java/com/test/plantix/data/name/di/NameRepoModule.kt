package com.test.plantix.data.name.di

import com.test.plantix.data.name.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.contracts.ExperimentalContracts

@Module
@InstallIn(SingletonComponent::class)
abstract class NameRepoModule {

    @Binds
    @Singleton
    abstract fun provideNamesRemoteSource(impl: NamesRemoteSourceImpl): NamesRemoteSource

    @Binds
    @Singleton
    abstract fun provideNameLocalSource(impl: NamesLocalSourceImpl):NamesLocalSource



    @ExperimentalContracts
    @Binds
    @Singleton
    abstract fun provideNameRepository(impl:NameRepositoryImpl):NameRepository
}