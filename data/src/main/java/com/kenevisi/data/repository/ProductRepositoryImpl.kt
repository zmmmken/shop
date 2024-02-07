package com.kenevisi.data.repository

import com.kenevisi.data.dataSource.RemoteProduceDataSource
import com.kenevisi.data.mappers.toProductEntity
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.domain.contract.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: RemoteProduceDataSource
) : ProductRepository {
    override suspend fun getProductInfo(productKey: String): ProductEntity {
        return dataSource.getProduct(productKey = productKey).toProductEntity()
    }

    override suspend fun getSimilarProducts(productKey: String): List<ProductEntity> {
        return emptyList()
    }
}