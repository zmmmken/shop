package com.kenevisi.domain.usecases

import com.kenevisi.core.exceptions.IoDispatcher
import com.kenevisi.domain.base.SuspendUseCase
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.domain.contract.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SuspendUseCase<GetProductUseCase.Params, ProductEntity>(dispatcher) {

    override suspend fun execute(input: Params): ProductEntity {
        return productRepository.getProductInfo(input.productKey)
    }

    data class Params(val productKey: String)

}