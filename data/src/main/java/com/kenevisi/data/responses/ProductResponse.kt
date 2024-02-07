package com.kenevisi.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    @SerialName("name1") val persianName: String?,
    @SerialName("name2") val latinName: String?,
    @SerialName("image_url") val posterImage: String?,
    @SerialName("price") val price: Long?,
    @SerialName("price_text") val priceText: String?,
    @SerialName("random_key") val productKey: String?,
    @SerialName("image_urls") val images: List<ImagesResponse>?
)

@Serializable
data class ImagesResponse(
    @SerialName("urls") val urls: List<String>
)
