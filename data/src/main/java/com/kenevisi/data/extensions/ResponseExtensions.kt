package com.kenevisi.data.extensions

import com.kenevisi.core.exceptions.TorobException
import com.kenevisi.data.responses.ErrorResponse
import kotlinx.serialization.json.Json
import retrofit2.Response

internal fun <T> Response<T>.bodyOrThrow(json: Json): T {
    if (isSuccessful) {
        return body()!!
    } else {
        throw parseError(json)
    }
}

internal fun <T> Response<T>.parseError(json: Json): Exception {
    val errorResponse = errorBody()?.string()?.let { json.decodeFromString<ErrorResponse>(it) }
    return TorobException(errorResponse?.error?.message.orEmpty())
}