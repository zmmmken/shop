package com.kenevisi.product.similarProducts

import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.product.databinding.ItemSimilarProductViewholderBinding
import com.kenevisi.product.presentation.SimilarProductAction

class SimilarProductViewHolder(
    private val binding: ItemSimilarProductViewholderBinding,
    private val imageLoader: ImageLoader,
    private val action: SimilarProductAction
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductEntity){
        product.getPosterImager()?.let { imageLoader.loadImage(binding.imgPoster, it) }
        binding.txtTitle.text = product.getPersianName()
        binding.root.setOnClickListener {
            action.onClick(product)
        }
    }

}