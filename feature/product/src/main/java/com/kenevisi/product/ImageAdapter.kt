package com.kenevisi.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kenevisi.feature_core.ImageLoader
import com.kenevisi.product.databinding.ImageViewHolderBinding

class ImageAdapter(private val imageLoader: ImageLoader) :
    ListAdapter<String, ImageViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            binding = ImageViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            imageLoader = imageLoader
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }


}