package com.kenevisi.data.di

import com.kenevisi.data.api.ProductApiRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteServicesModule {

    @Provides
    @Singleton
    fun provideSupervisorApiRemoteService(retrofit: Retrofit): ProductApiRemoteService {
        return retrofit.create(ProductApiRemoteService::class.java)
    }
}