package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PoolsHashrateItem(
    @Json(name = "avgHashrate")
    val avgHashrate: String,
    @Json(name = "poolName")
    val poolName: String,
    @Json(name = "share")
    val share: Double,
    @Json(name = "timestamp")
    val timestamp: Int
)