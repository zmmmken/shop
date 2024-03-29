package com.kenevisi.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.ImageLoader
import com.kenevisi.feature_core.extensions.canNavigateUp
import com.kenevisi.feature_core.extensions.collectOnEachStart
import com.kenevisi.feature_core.extensions.getScreenWidthInDp
import com.kenevisi.product.databinding.FragmentProductBinding
import com.kenevisi.product.presentation.ProductAction
import com.kenevisi.product.presentation.SimilarProductAction
import com.kenevisi.product.similarProducts.ProductInfoAdapter
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

    private val viewModel: ProductViewModel by viewModels()
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
        productAdapter = ProductInfoAdapter(imageLoader) {
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
        initNavigationOnToolbar()
    }

    private fun initNavigationOnToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.navigationIcon = if(findNavController().canNavigateUp()) {
            getDrawable(requireContext(), com.kenevisi.feature_core.R.drawable.ic_arrow)
        } else null

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
        val columnCount = calculateColumnCount()
        val gridLayoutManager = GridLayoutManager(requireContext(), columnCount)
        gridLayoutManager.orientation = GridLayoutManager.VERTICAL // For horizontal orientation
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // Define span size for each position
                return if (position < (productAdapter?.itemCount ?: 0)) {
                    columnCount
                } else {
                    val isLoadStateAdapter =
                        binding.rvSimilarPosts.adapter?.itemCount?.minus(1) == position
                    if (isLoadStateAdapter) {
                        columnCount
                    } else 1
                }

            }
        }
        binding.rvSimilarPosts.layoutManager = gridLayoutManager
        binding.rvSimilarPosts.adapter = ConcatAdapter(
            productAdapter,
            similarProductAdapter?.withAppendAndRefreshLoadState(
                footer = footerLoadStateAdapter,
                refresh = footerLoadStateAdapterForRefresh
            )
        )
        binding.rvSimilarPosts.itemAnimator = null
    }

    private fun calculateColumnCount(): Int {
        return try {
            requireContext().getScreenWidthInDp() / IDEAL_WIDTH_SIMILAR_ITEM
        } catch (_: Exception) {
            DEFAULT_COLUMN_COUNT
        }
    }


    override fun onDestroyView() {
        binding.rvSimilarPosts.adapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        similarProductAdapter = null
        footerLoadStateAdapter = null
        footerLoadStateAdapterForRefresh = null
        productAdapter = null
    }

    companion object {
        private const val IDEAL_WIDTH_SIMILAR_ITEM = 160
        private const val DEFAULT_COLUMN_COUNT = 2
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
