package com.kenevisi.product

import androidx.paging.PagingData
import com.kenevisi.core.exceptions.ResourceState
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.domain.usecases.GetProductUseCase
import com.kenevisi.domain.usecases.GetSimilarProductsUseCase
import com.kenevisi.testing.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var getProductUseCase: GetProductUseCase

    @RelaxedMockK
    private lateinit var getSimilarProductsUseCase: GetSimilarProductsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }


    @Test
    fun initViewModel() = runTest {
        val productKey = "temp"
        createViewModel(productKey)
        coEvery { getProductUseCase(any()) } returns ResourceState.Success(ProductEntity.empty())

        val slotGetProduct = slot<GetProductUseCase.Params>()
        val slotGetSimilarProduct = slot<GetSimilarProductsUseCase.Params>()


        coVerify(exactly = 1) { getProductUseCase(capture((slotGetProduct))) }
        coVerify(exactly = 1) { getSimilarProductsUseCase(capture(slotGetSimilarProduct)) }

        Assert.assertEquals(productKey, slotGetProduct.captured.productKey)
        Assert.assertEquals(productKey, slotGetSimilarProduct.captured.productKey)
    }

    @Test
    fun updateUiAfterGetProductIsSuccess() = runTest {
        val productKey = "mahdi"
        val persianName = "mohammad"
        val useCaseResponse = ProductEntity.empty().copy(persianName = persianName)
        coEvery { getProductUseCase(any()) } returns ResourceState.Success(useCaseResponse)
        val viewModel = createViewModel(productKey)
        Assert.assertEquals(
            useCaseResponse,
            viewModel.container.uiState.value.product.data
        )
    }

    @Test
    fun useOldValueIfGetProductHasError() = runTest {
        val productKey = "mahdi"
        coEvery { getProductUseCase(any()) } returns ResourceState.Failure(Exception())
        val similarProducts = MutableStateFlow(PagingData.empty<ProductEntity>())
        every { getSimilarProductsUseCase(any()) } returns similarProducts
        val viewModel = createViewModel(productKey)
        Assert.assertEquals(
            productKey,
            (viewModel.container.uiState.value.product as? ResourceState.Failure)?.oldData?.getProductKey()
        )
    }


    private fun createViewModel(
        productKey: String,
    ) = ProductViewModel(
        savedStateHandle = ProductFragmentArgs(
            perianName = "",
            latinName = "",
            posterUrl = "",
            productId = productKey,
        ).toSavedStateHandle(),
        getProductUseCase = getProductUseCase,
        getSimilarProductsUseCase = getSimilarProductsUseCase,
    )

}