package com.kenevisi.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kenevisi.core.exceptions.ResourceState
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.domain.usecases.GetProductUseCase
import com.kenevisi.domain.usecases.GetSimilarProductsUseCase
import com.kenevisi.feature_core.viewModelHelper.ViewModelContainer
import com.kenevisi.feature_core.viewModelHelper.ViewModelHost
import com.kenevisi.product.presentation.ProductAction
import com.kenevisi.product.presentation.ProductSideEffect
import com.kenevisi.product.presentation.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductUseCase: GetProductUseCase,
    private val getSimilarProductsUseCase: GetSimilarProductsUseCase,
) : ViewModel(), ViewModelHost<ProductUiState, ProductSideEffect, ProductAction> {
    private val args by lazy { ProductFragmentArgs.fromSavedStateHandle(savedStateHandle) }
    private var getProductJob: Job? = null
    override val container = ViewModelContainer<ProductUiState, ProductSideEffect>(
        scope = viewModelScope,
        initState = ProductUiState(
            product = ResourceState.Loading(
                oldData = ProductEntity.empty().copy(
                    persianName = args.perianName,
                    latinName = args.latinName,
                    posterImage = args.posterUrl,
                    productKey = args.productId
                )
            )
        )
    )

    init {
        getProduct()
        getSimilarProducts()
    }

    override fun handleAction(action: ProductAction) {
        when (action) {
            ProductAction.GetProduct -> {
                reduce { copy(product= ResourceState.Loading(container.uiState.value.product.data)) }
                getProduct()
            }
        }
    }


    private fun getProduct() {
        getProductJob?.cancel()
        getProductJob = viewModelScope.launch {
            getProductUseCase(
                input = GetProductUseCase.Params(
                    productKey = args.productId
                )
            ).let {
                if (it is ResourceState.Failure && container.uiState.value.product.data != null) {
                    reduce {
                        copy(
                            product = ResourceState.Failure(
                                it.exception,
                                container.uiState.value.product.data
                            )
                        )
                    }
                } else {
                    reduce { copy(product = it) }
                }
            }
        }
    }

    private fun getSimilarProducts() {
        viewModelScope.launch {
            getSimilarProductsUseCase(
                input = GetSimilarProductsUseCase.Params(
                    productKey = args.productId
                )
            )
                .cachedIn(viewModelScope)
                .collectLatest {
                    reduce { copy(similarProducts = it) }
                }
        }
    }

}
