package com.kenevisi.product

import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.product.databinding.ImageViewHolderBinding

class ImageViewHolder(
    private val binding: ImageViewHolderBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUrl: String){
        imageLoader.loadImage(binding.root,imageUrl)
    }
}