package com.kenevisi.domain.usecases

import androidx.paging.PagingData
import com.kenevisi.core.exceptions.IoDispatcher
import com.kenevisi.domain.base.PagingDataUseCase
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.domain.contract.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : PagingDataUseCase<GetProductUseCase.Params, ProductEntity>(dispatcher) {

    override fun execute(input: GetProductUseCase.Params): Flow<PagingData<ProductEntity>> {
        return productRepository.getSimilarProducts(input.productKey)
    }

    data class Params(val productKey: String)
}