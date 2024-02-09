package com.kenevisi.product.presentation

sealed interface ProductAction {
    data object GetProduct: ProductAction
}