package com.example.myappnotifications


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("title")
    var title: String? = null
)