package com.kenevisi.core.exceptions

sealed class ResourceState<out T> {
    data class Success<out T>(val data: T) : ResourceState<T>()
    data class Failure(val exception: Exception) : ResourceState<Nothing>()
    data object Loading : ResourceState<Nothing>()

    fun getData(): T? {
        return (this as? Success)?.data
    }

    fun isLoading() = this is Loading

    fun onSuccess(callBack: (T) -> Unit) = run {
        getData()?.let(callBack)
        this
    }

    fun onFailure(callBack: (Exception) -> Unit) = run {
        (this as? Failure)?.exception?.let(callBack)
        this
    }

    fun onLoading(callBack: () -> Unit) = run {
        if (this is Loading) callBack()
        this
    }

}
