package com.kenevisi.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kenevisi.data.dataSource.RemoteProductDataSource
import com.kenevisi.data.responses.ProductResponse

class SimilarProductPagingSource(
    private val productKey: String,
    private val remoteProductDataSource: RemoteProductDataSource
) : PagingSource<Int, ProductResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ProductResponse>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse> {
        val currentKey = params.key ?: 0
        return try {
            val response = remoteProductDataSource.getSimilarProduct(
                productKey = productKey,
                size = params.loadSize,
                page = currentKey
            )

            LoadResult.Page(
                data = response.results.orEmpty(),
                prevKey = if (currentKey != 0) currentKey.minus(1) else null,
                nextKey = if (response.next != null) currentKey.plus(1) else null
            )
        } catch (e : Exception){
            LoadResult.Error(
                throwable = e
            )
        }
    }
}