package com.kenevisi.data.dataSource

import com.kenevisi.data.api.ProductApiRemoteService
import com.kenevisi.data.responses.ProductResponse

class RemoteProductDataSourceImpl(
    private val remoteService: ProductApiRemoteService
) : RemoteProduceDataSource {
    override suspend fun getProduct(productId:String): ProductResponse {
        return remoteService.getProduct(productId).body()!!
    }
}