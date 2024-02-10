package com.kenevisi.feature_core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


fun <T> Fragment.collectOnEachStart(flow: Flow<T>, collector: suspend (T) -> Unit): Job {
    return lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collector)
        }
    }
}

fun <T> Fragment.collectOnEachStart(
    flow: MutableStateFlow<T>,
    collector: suspend (T) -> Unit
): Job {
    return lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collector)
        }
    }
}

fun <T> Fragment.collectOnEachResume(flow: Flow<T>, collector: suspend (T) -> Unit): Job {
    return lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            flow.collectLatest(collector)
        }
    }
}

fun <T> Fragment.collectOnEachResume(
    flow: MutableStateFlow<T>,
    collector: suspend (T) -> Unit
): Job {
    return lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            flow.collectLatest(collector)
        }
    }
}