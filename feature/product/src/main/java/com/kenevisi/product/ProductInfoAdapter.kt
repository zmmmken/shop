package com.kenevisi.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kenevisi.core.exceptions.ResourceState
import com.kenevisi.domain.contract.ProductEntity
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import com.kenevisi.product.databinding.ItemProductViewHolderBinding

class ProductInfoAdapter(
    private val imageLoader: ImageLoader,
    private val action: ProductViewHolderAction,
) : ListAdapter<ResourceState<ProductEntity>, ProductViewHolder>(diffUtil) {


    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ResourceState<ProductEntity>>() {
            override fun areItemsTheSame(
                oldItem: ResourceState<ProductEntity>,
                newItem: ResourceState<ProductEntity>
            ): Boolean {
                return oldItem.data?.getProductKey() == newItem.data?.getProductKey()
            }

            override fun areContentsTheSame(
                oldItem: ResourceState<ProductEntity>,
                newItem: ResourceState<ProductEntity>
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            binding = ItemProductViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            imageLoader = imageLoader,
            action = action
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewAttachedToWindow(holder: ProductViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.addAdapterToImageList()
    }

    override fun onViewDetachedFromWindow(holder: ProductViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.removeAdapterFromImageList()
    }
}