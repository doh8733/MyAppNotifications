package com.example.myappnotifications


import com.google.gson.annotations.SerializedName

data class NotificationsRequest(
    @SerializedName("data")
    var data: Data? = Data(),
    @SerializedName("to")
    var to: String? = null
)