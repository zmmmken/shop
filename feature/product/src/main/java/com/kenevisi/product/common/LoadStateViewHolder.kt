package com.kenevisi.product.common

import android.util.Log
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.kenevisi.product.databinding.LoadStateLayoutBinding

class LoadStateViewHolder(
    private val binding: LoadStateLayoutBinding,
    private val action: LoadStateAction,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState){
        binding.imgRetry.isVisible = loadState is LoadState.Error
        binding.txtRetry.isVisible = loadState is LoadState.Error
        binding.root.setOnClickListener {
            if(loadState is LoadState.Error){
                action.retry()
            }
        }
        binding.piLoading.isVisible = (loadState is LoadState.Loading).apply {
            Log.d("TAG1234", "bind: isLoading: $this")
        }
    }

}