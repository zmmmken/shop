package com.kenevisi.product

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.product.databinding.ItemProductViewHolderBinding

class ProductViewHolder(
    private val binding: ItemProductViewHolderBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(binding.root) {

    private var imageAdapter: ImageAdapter = ImageAdapter(imageLoader)
    fun bind(productEntity: ProductEntity) {
        binding.txtLatinNameName.text = productEntity.getLatinName()
        binding.txtPersianName.text = productEntity.getPersianName()
        initImageList()
        setImages(productEntity.getImages())
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
}