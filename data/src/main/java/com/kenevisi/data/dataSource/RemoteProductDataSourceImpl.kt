package com.kenevisi.data.dataSource

import com.kenevisi.data.api.ProductApiRemoteService
import com.kenevisi.data.extensions.bodyOrThrow
import com.kenevisi.data.responses.ProductResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RemoteProductDataSourceImpl @Inject constructor(
    private val remoteService: ProductApiRemoteService,
    private val json: Json
) : RemoteProduceDataSource {
    override suspend fun getProduct(productKey: String): ProductResponse {
        return remoteService.getProduct(productKey).bodyOrThrow(json)
    }
}

