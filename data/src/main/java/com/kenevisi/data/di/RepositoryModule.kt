package com.kenevisi.data.di

import com.kenevisi.data.repository.ProductRepositoryImpl
import com.kenevisi.domain.contract.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repository: ProductRepositoryImpl
    ): ProductRepository
}