package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BlockCount(
    @Json(name = "all")
    val all: Int,
    @Json(name = "24h")
    val h: Int,
    @Json(name = "1w")
    val w: Int
)