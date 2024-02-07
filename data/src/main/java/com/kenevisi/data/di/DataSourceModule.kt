package com.kenevisi.data.di

import com.kenevisi.data.dataSource.RemoteProductDataSource
import com.kenevisi.data.dataSource.RemoteProductDataSourceImpl
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
    abstract fun bindProductRemoteDataSource(
        dataSource: RemoteProductDataSourceImpl
    ): RemoteProductDataSource
}