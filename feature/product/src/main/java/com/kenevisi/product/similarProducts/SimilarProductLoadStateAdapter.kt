package com.kenevisi.product.similarProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.kenevisi.product.common.LoadStateAction
import com.kenevisi.product.common.LoadStateViewHolder
import com.kenevisi.product.databinding.LoadStateLayoutBinding

class SimilarProductLoadStateAdapter(
    private val loadStateAction: LoadStateAction
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            binding = LoadStateLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action = loadStateAction
        )
    }
}