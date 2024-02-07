package com.kenevisi.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("error") val error: ErrorMessageResponse?
)

@Serializable
data class ErrorMessageResponse(
    @SerialName("message") val message: String?
)