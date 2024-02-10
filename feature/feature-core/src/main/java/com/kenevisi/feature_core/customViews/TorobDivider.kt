package com.kenevisi.feature_core.customViews

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.divider.MaterialDivider

class TorobDivider : MaterialDivider{
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}