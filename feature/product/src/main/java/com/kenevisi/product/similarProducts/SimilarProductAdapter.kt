package com.kenevisi.product.similarProducts

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.product.databinding.ItemSimilarProductViewholderBinding

class SimilarProductAdapter(
    private val imageLoader: ImageLoader
) : PagingDataAdapter<ProductEntity, SimilarProductViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: SimilarProductViewHolder, position: Int) {
        Log.d("TAG1234", "onBindViewHolder: ${getItem(position)?.getProductKey()}")
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarProductViewHolder {
        return SimilarProductViewHolder(
            binding = ItemSimilarProductViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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