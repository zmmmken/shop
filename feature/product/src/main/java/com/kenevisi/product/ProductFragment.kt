package com.kenevisi.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kenevisi.core.exceptions.ResourceState
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.collectOnEachStart
import com.kenevisi.product.databinding.FragmentProductBinding
import com.kenevisi.product.similarProducts.SimilarProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding
        get() = _binding!!

    val viewModel: ProductViewModel by viewModels()
    private var similarProductAdapter: SimilarProductAdapter? = null
    private var productAdapter: ProductInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        similarProductAdapter = SimilarProductAdapter()
        productAdapter = ProductInfoAdapter()
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
    }

    private fun collectUiState() {
        collectOnEachStart(viewModel.container.uiState.map { it.product }.distinctUntilChanged()) { productState ->
            if(productState !is ResourceState.Success){
                productAdapter?.submitList(listOf(ProductEntity(null,null,null,null,null,null,null)))
            }

            productState.onSuccess {
                productAdapter?.submitList(listOf(it))
            }

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
                if(position < 1){
                    return 2
                }
                return 1
            }
        }
        binding.rvSimilarPosts.layoutManager = gridLayoutManager
        binding.rvSimilarPosts.adapter = ConcatAdapter(productAdapter,similarProductAdapter)
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