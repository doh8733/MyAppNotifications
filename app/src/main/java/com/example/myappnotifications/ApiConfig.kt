package com.example.myappnotifications

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiConfig {
    @POST("/fcm/send")
    @Headers("Content-Type:application/json")
    fun postNotifications(
        @Header("Authorization") token :String,
        @Body notificationsRequest: NotificationsRequest) :retrofit2.Call<NotificationsResponse>
}