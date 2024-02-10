package com.kenevisi.feature_core.customViews

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.MaterialToolbar

class TorobToolBar : MaterialToolbar {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}