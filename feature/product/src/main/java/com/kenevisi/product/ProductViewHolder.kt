package com.kenevisi.product

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.core.exceptions.ResourceState
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.feature_core.viewModelHelper.gone
import com.kenevisi.feature_core.viewModelHelper.invisible
import com.kenevisi.feature_core.viewModelHelper.show
import com.kenevisi.product.databinding.ItemProductViewHolderBinding

class ProductViewHolder(
    private val binding: ItemProductViewHolderBinding,
    private val imageLoader: ImageLoader,
    private val action: ProductViewHolderAction
) : RecyclerView.ViewHolder(binding.root) {

    private var imageAdapter: ImageAdapter = ImageAdapter(imageLoader)
    private val successViews by lazy {
        binding.run {
            listOf(
                dividerTopActions,
                dividerBottomActions,
                imgBell,
                imgHeart,
                imgShare,
                txtPriceNotifier
            )
        }
    }

    private val loadingViews by lazy {
        binding.run {
            listOf(stateLoading.root)
        }
    }
    private val errorViews by lazy {
        binding.run {
            listOf(stateError.root)
        }
    }

    fun bind(productEntity: ResourceState<ProductEntity>) {
        binding.txtLatinNameName.text = productEntity.data?.getLatinName()
        binding.txtPersianName.text = productEntity.data?.getPersianName()
        initImageList()
        productEntity.data?.let { setImages(it.getImages()) }
        handleStates(productEntity)
        binding.stateError.root.setOnClickListener { action.retry() }
    }


    private fun handleStates(productEntity: ResourceState<ProductEntity>) {
        when (productEntity) {
            is ResourceState.Failure -> showErrorState()
            is ResourceState.Loading -> showLoadingState()
            is ResourceState.None -> showSuccessState()
            is ResourceState.Success -> showSuccessState()
        }
    }

    private fun showLoadingState() {
        successViews.hide()
        loadingViews.show()
        errorViews.gone()

    }

    private fun showErrorState() {
        successViews.hide()
        errorViews.show()
        loadingViews.gone()
    }

    private fun showSuccessState() {
        successViews.show()
        errorViews.gone()
        loadingViews.gone()
    }


    private fun initImageList() {
        binding.rvImagesList.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    fun addAdapterToImageList() {
        binding.rvImagesList.adapter = imageAdapter
    }

    fun removeAdapterFromImageList() {
        binding.rvImagesList.adapter = imageAdapter
    }

    private fun setImages(items: List<String>) {
        imageAdapter.submitList(items)
    }


    private fun List<View>.gone() {
        forEach { it.gone() }
    }

    private fun List<View>.show() {
        forEach { it.show() }
    }

    private fun List<View>.hide() {
        forEach { it.invisible() }
    }
}