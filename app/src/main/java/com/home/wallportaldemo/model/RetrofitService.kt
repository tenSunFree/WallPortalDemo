package com.home.wallportaldemo.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    private val client: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}