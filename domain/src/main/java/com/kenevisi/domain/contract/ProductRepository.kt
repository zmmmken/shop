package com.kenevisi.domain.contract

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductInfo(productKey: String): ProductEntity
    fun getSimilarProducts(productKey: String): Flow<PagingData<ProductEntity>>
}