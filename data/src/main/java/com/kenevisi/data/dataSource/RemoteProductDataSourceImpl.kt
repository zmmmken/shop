package com.kenevisi.data.dataSource

import com.kenevisi.data.api.ProductApiRemoteService
import com.kenevisi.data.extensions.bodyOrThrow
import com.kenevisi.data.responses.ProductResponse
import com.kenevisi.data.responses.SimilarProductResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RemoteProductDataSourceImpl @Inject constructor(
    private val remoteService: ProductApiRemoteService,
    private val json: Json
) : RemoteProductDataSource {
    override suspend fun getProduct(productKey: String): ProductResponse {
        return remoteService.getProduct(productKey).bodyOrThrow(json)
    }

    override suspend fun getSimilarProduct(
        productKey: String,
        page: Int,
        size: Int
    ): SimilarProductResponse {
        return remoteService.getSimilarProducts(
            productId = productKey,
            page = page,
            size = size
        ).bodyOrThrow(json)
    }
}

