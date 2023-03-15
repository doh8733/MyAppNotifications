package com.example.myappnotifications


import com.google.gson.annotations.SerializedName

data class NotificationsResponse(
    @SerializedName("canonical_ids")
    var canonicalIds: Int? = null,
    @SerializedName("failure")
    var failure: Int?= null,
    @SerializedName("multicast_id")
    var multicastId: Long?= null,
    @SerializedName("results")
    var results: List<Result?>?= null,
    @SerializedName("success")
    var success: Int?= null
)