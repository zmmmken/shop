package com.kenevisi.product.presentation

import androidx.paging.PagingData
import com.kenevisi.core.exceptions.ResourceState
import com.kenevisi.domain.contract.ProductEntity

data class ProductUiState(
    val product: ResourceState<ProductEntity> = ResourceState.None(null),
    val similarProducts: PagingData<ProductEntity> = PagingData.empty(),
)