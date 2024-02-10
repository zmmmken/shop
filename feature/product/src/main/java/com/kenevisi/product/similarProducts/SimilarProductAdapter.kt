package com.kenevisi.product.similarProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.ImageLoader
import com.kenevisi.product.databinding.ItemSimilarProductViewholderBinding
import com.kenevisi.product.presentation.SimilarProductAction

class SimilarProductAdapter(
    private val imageLoader: ImageLoader,
    private val action: SimilarProductAction
) : PagingDataAdapter<ProductEntity, SimilarProductViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: SimilarProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarProductViewHolder {
        return SimilarProductViewHolder(
            binding = ItemSimilarProductViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            imageLoader = imageLoader,
            action = action
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.getProductKey() == newItem.getProductKey()
            }

            override fun areContentsTheSame(
                oldItem: ProductEntity,
                newItem: ProductEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}