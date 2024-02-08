package com.kenevisi.product.similarProducts

import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.product.databinding.ItemSimilarProductViewholderBinding

class SimilarProductViewHolder(
    private val binding: ItemSimilarProductViewholderBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductEntity){
        product.getPosterImager()?.let { imageLoader.loadImage(binding.imgPoster, it) }
        binding.txtTitle.text = product.getPersianName()
    }

}