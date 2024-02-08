package com.kenevisi.data.mappers

import com.kenevisi.data.responses.ProductResponse
import com.kenevisi.domain.contract.ProductEntity

fun ProductResponse.toProductEntity(): ProductEntity {
    return ProductEntity(
        persianName = persianName,
        latinName = latinName,
        posterImage = posterImage,
        price = price,
        priceText = priceText,
        productKey = productKey,
        images = images?.firstOrNull()?.urls,
        shopText = shopText
    )
}