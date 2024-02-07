package com.kenevisi.domain.contract

interface ProductRepository {
    fun getProductInfo(productKey: String)
    fun getSimilarProducts(productKey: String)
}