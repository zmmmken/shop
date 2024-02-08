package com.kenevisi.product.presentation

import com.kenevisi.domain.contract.ProductEntity

interface SimilarProductAction {
    fun onClick(product: ProductEntity)
}