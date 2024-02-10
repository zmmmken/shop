package com.kenevisi.feature_core.extensions

import android.content.Context

 fun Context.getScreenWidthInDp(): Int {
    val displayMetrics = resources.displayMetrics
    val screenWidthInPixels = displayMetrics.widthPixels.toFloat()
    val density = displayMetrics.densityDpi.toFloat()
    return (screenWidthInPixels / (density / 160f)).toInt()
}