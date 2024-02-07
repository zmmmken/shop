package com.kenevisi.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimilarProductResponse(
    @SerialName("results") val results: List<ProductResponse>
)