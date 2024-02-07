package com.kenevisi.data.api

import com.kenevisi.data.responses.ProductResponse
import com.kenevisi.data.responses.SimilarProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiRemoteService {
    @GET("$API_PREFIX/details/")
    suspend fun getProduct(
        @Query("prk") productId: String
    ): Response<ProductResponse>

    @GET("$API_PREFIX/similar-base-product/")
    suspend fun getSimilarProducts(
        @Query("prk") productId: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SimilarProductResponse>

    companion object {
        private const val API_PREFIX = "/v4/base-product"
    }
}