package com.kenevisi.feature_core

import androidx.appcompat.widget.AppCompatImageView


interface ImageLoader {
    fun loadImage(view: AppCompatImageView, url: String)

}