package com.example.myappnotifications

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private var BASE_URL = "https://fcm.googleapis.com/"
    private val retrofitClient =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    val apiConfig = retrofitClient.create(ApiConfig::class.java)
}