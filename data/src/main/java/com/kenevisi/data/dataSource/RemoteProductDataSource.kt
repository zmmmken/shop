package com.kenevisi.data.dataSource

import com.kenevisi.data.responses.ProductResponse
import com.kenevisi.data.responses.SimilarProductResponse

interface RemoteProductDataSource {
    suspend fun getProduct(productKey: String): ProductResponse
    suspend fun getSimilarProduct(
        productKey: String,
        page: Int,
        size: Int
    ): SimilarProductResponse
}
