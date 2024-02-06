package com.kenevisi.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenevisi.feature_core.viewModelHelper.ViewModelContainer
import com.kenevisi.feature_core.viewModelHelper.ViewModelHost
import com.kenevisi.product.presentation.ProductAction
import com.kenevisi.product.presentation.ProductSideEffect
import com.kenevisi.product.presentation.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), ViewModelHost<ProductUiState, ProductSideEffect, ProductAction> {
    private val args by lazy { ProductFragmentArgs.fromSavedStateHandle(savedStateHandle) }

    override val container = ViewModelContainer<ProductUiState, ProductSideEffect>(
        scope = viewModelScope,
        initState = ProductUiState()
    )
    override fun handleAction(action: ProductAction) {

    }

}
