package com.kenevisi.data.dataSource

import com.kenevisi.data.api.ProductApiRemoteService
import com.kenevisi.data.responses.ProductResponse
import javax.inject.Inject

class RemoteProductDataSourceImpl @Inject constructor(
    private val remoteService: ProductApiRemoteService
) : RemoteProduceDataSource {
    override suspend fun getProduct(productKey:String): ProductResponse {
        return remoteService.getProduct(productKey).body()!!
    }
}