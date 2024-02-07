package com.kenevisi.domain.contract

interface ProductRepository {
    suspend fun getProductInfo(productKey: String): ProductEntity
    suspend fun getSimilarProducts(productKey: String): List<ProductEntity>
}