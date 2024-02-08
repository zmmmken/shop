package com.kenevisi.torobenterance.di

import com.kenevisi.torobenterance.GlideImageLoader
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageLoaderModule {
    @Binds
    abstract fun bindImageLoader(imageLoader: GlideImageLoader):ImageLoader
}