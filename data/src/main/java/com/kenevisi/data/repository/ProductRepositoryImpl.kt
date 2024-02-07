package com.kenevisi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kenevisi.data.dataSource.RemoteProductDataSource
import com.kenevisi.data.mappers.toProductEntity
import com.kenevisi.data.responses.ProductResponse
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.domain.contract.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: RemoteProductDataSource
) : ProductRepository {
    override suspend fun getProductInfo(productKey: String): ProductEntity {
        return dataSource.getProduct(productKey = productKey).toProductEntity()
    }

    override suspend fun getSimilarProducts(productKey: String): Flow<PagingData<ProductEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                SimilarProductPagingSource(
                    productKey = productKey,
                    remoteProductDataSource = dataSource
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toProductEntity() }
        }
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}