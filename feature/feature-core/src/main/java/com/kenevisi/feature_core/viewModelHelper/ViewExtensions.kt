package com.kenevisi.feature_core.viewModelHelper

import android.view.View
import androidx.core.view.isVisible

fun View.gone(){
    isVisible = false
}
fun View.show(){
    isVisible = true
}
fun View.invisible(){
    visibility = View.INVISIBLE
}
