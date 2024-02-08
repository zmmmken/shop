package com.kenevisi.torobenterance

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.kenevisi.feature_core.viewModelHelper.ImageLoader
import javax.inject.Inject

class GlideImageLoader @Inject constructor(): ImageLoader {
    override fun loadImage(view: AppCompatImageView, url: String) {
        Glide.with(view).load(url).into(view)
    }
}