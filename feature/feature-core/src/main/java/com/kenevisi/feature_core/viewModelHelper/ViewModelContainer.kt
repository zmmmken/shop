package com.kenevisi.feature_core.viewModelHelper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelContainer<ST, EF>(
    val scope: CoroutineScope,
    initState: ST
) {

    private val _uiState = MutableStateFlow(initState)
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<EF>()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun reduce(reducer: ST.() -> ST) {
        _uiState.update { reducer(it) }
    }

    fun postSideEffect(newSideEffect: EF) {
        scope.launch {
            _sideEffect.send(newSideEffect)
        }
    }
}
