package com.kenevisi.core.exceptions

sealed class ResourceState<T>(open val data: T?) {
    data class Success<T>(override val data: T) : ResourceState<T>(data)
    data class Failure<T>(val exception: Exception,val oldData:T? = null) : ResourceState<T>(oldData)
    data class Loading<T>(val oldData:T? = null) : ResourceState<T>(oldData)
    data class None<T>(val oldData:T? = null) : ResourceState<T>(oldData)

    fun getResult(): T? {
        return (this as? Success)?.data
    }

    fun isLoading() = this is Loading

    fun onSuccess(callBack: (T) -> Unit) = run {
        getResult()?.let(callBack)
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
