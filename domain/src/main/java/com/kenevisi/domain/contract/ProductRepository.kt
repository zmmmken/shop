package com.kenevisi.domain.contract

interface ProductRepository {
    fun getProductInfo(productKey: String): ProductEntity
    fun getSimilarProducts(productKey: String): List<ProductEntity>
}