package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlockShare(
    @Json(name = "all")
    val all: Double,
    @Json(name = "24h")
    val h: Double,
    @Json(name = "1w")
    val w: Double
)