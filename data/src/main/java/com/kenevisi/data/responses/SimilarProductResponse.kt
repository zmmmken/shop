package com.kenevisi.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class SimilarProductResponse(
    val results: List<ProductResponse>
)