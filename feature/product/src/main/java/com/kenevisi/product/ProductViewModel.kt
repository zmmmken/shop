package com.kenevisi.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kenevisi.product.presentation.ProductAction
import com.kenevisi.product.presentation.ProductSideEffect
import com.kenevisi.product.presentation.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args by lazy {  ProductFragmentArgs.fromSavedStateHandle(savedStateHandle) }

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<ProductSideEffect>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun handleAction(action: ProductAction) {

    }
}