package com.home.wallportaldemo.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("comments")
    suspend fun getShoppingDesignResponse(
        @Query("postId") postId: Int
    ): Response<List<ShoppingDesignResponse>>
}