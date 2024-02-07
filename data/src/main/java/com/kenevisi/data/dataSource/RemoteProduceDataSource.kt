package com.kenevisi.data.dataSource

import com.kenevisi.data.responses.ProductResponse

interface RemoteProduceDataSource {
    suspend fun getProduct(productKey: String): ProductResponse
}
