package com.kenevisi.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.feature_core.viewModelHelper.collectOnEachStart
import com.kenevisi.product.databinding.FragmentProductBinding
import com.kenevisi.product.presentation.ProductAction
import com.kenevisi.product.presentation.SimilarProductAction
import com.kenevisi.product.similarProducts.SimilarProductAdapter
import com.kenevisi.product.similarProducts.SimilarProductLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding
        get() = _binding!!

    val viewModel: ProductViewModel by viewModels()
    private var similarProductAdapter: SimilarProductAdapter? = null
    private var productAdapter: ProductInfoAdapter? = null
    private var footerLoadStateAdapter: SimilarProductLoadStateAdapter? = null
    private var footerLoadStateAdapterForRefresh: SimilarProductLoadStateAdapter? = null


    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        similarProductAdapter = SimilarProductAdapter(
            imageLoader = imageLoader,
            action = object : SimilarProductAction {
                override fun onClick(product: ProductEntity) {
                    findNavController().navigate(
                        ProductFragmentDirections.actionFragmentProductToSelf(
                            productId = product.getProductKey(),
                            latinName = product.getLatinName(),
                            perianName = product.getPersianName(),
                            posterUrl = product.getPosterImager()
                        )
                    )
                }
            }
        )
        productAdapter = ProductInfoAdapter(imageLoader){
            viewModel.handleAction(ProductAction.GetProduct)
        }
        footerLoadStateAdapter = SimilarProductLoadStateAdapter {
            similarProductAdapter?.retry()
        }
        footerLoadStateAdapterForRefresh = SimilarProductLoadStateAdapter {
            similarProductAdapter?.retry()
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayoutManager()
        collectUiState()
        onNavigationIconClick()
    }

    private fun onNavigationIconClick() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun collectUiState() {
        collectOnEachStart(viewModel.container.uiState.map { it.product }
            .distinctUntilChanged()) { productState ->
            binding.toolbar.title = productState.data?.getPersianName()

            productAdapter?.submitList(listOf(productState))

        }
        collectOnEachStart(viewModel.container.uiState.map { it.similarProducts }
            .distinctUntilChanged()) {
            similarProductAdapter?.submitData(it)
        }
    }

    private fun initLayoutManager() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        gridLayoutManager.orientation = GridLayoutManager.VERTICAL // For horizontal orientation
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // Define span size for each position
                return if (position < (productAdapter?.itemCount ?: 0)) {
                    2
                } else {
                    val isLoadStateAdapter =
                        binding.rvSimilarPosts.adapter?.itemCount?.minus(1) == position
                    if (isLoadStateAdapter) {
                        2
                    } else 1
                }

            }
        }
        binding.rvSimilarPosts.layoutManager = gridLayoutManager
        binding.rvSimilarPosts.adapter = ConcatAdapter(productAdapter,
            similarProductAdapter?.withAppendAndRefreshLoadState(
                footer = footerLoadStateAdapter,
                refresh = footerLoadStateAdapterForRefresh
            )
        )
        binding.rvSimilarPosts.itemAnimator = null
    }

    override fun onDestroyView() {
        binding.rvSimilarPosts.adapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        similarProductAdapter = null
    }

}

fun <T : Any, VH : RecyclerView.ViewHolder> PagingDataAdapter<T, VH>.withAppendAndRefreshLoadState(
    footer: LoadStateAdapter<*>?,
    refresh: LoadStateAdapter<*>?
): ConcatAdapter {
    addLoadStateListener { loadStates ->
        footer?.loadState = loadStates.append
        refresh?.loadState = loadStates.refresh
    }
    return ConcatAdapter(refresh, this, footer)
}
