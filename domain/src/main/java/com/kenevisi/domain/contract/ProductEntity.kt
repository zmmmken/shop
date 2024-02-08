package com.kenevisi.domain.contract

data class ProductEntity(
    private val persianName: String?,
    private val latinName: String?,
    private val posterImage: String?,
    private val price: Long?,
    private val priceText: String?,
    private val productKey: String?,
    private val images: List<String>?
) {
    fun getProductKey() = productKey.orEmpty()

    fun getPersianName() = persianName.orEmpty()
    fun getLatinName() = latinName.orEmpty()

    fun getImages() = images.orEmpty()

    companion object {
        fun empty() = ProductEntity(
            persianName = null,
            latinName = null,
            posterImage = null,
            productKey = null,
            price = null,
            priceText = null,
            images = null
        )
    }
}