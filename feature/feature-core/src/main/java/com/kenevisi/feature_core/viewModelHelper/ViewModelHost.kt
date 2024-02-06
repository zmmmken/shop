package com.kenevisi.feature_core.viewModelHelper

interface ViewModelHost<ST, EF, AC> {
    val container: ViewModelContainer<ST, EF>
    fun reduce(reducer: ST.() -> ST) {
        container.reduce(reducer)
    }

    fun postSideEffect(newSideEffect: EF) {
        container.postSideEffect(newSideEffect)
    }

    fun handleAction(action: AC)
}