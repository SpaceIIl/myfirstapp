package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PoolX(
    @Json(name = "avgMatchRate")
    val avgMatchRate: Double?,
    @Json(name = "blockCount")
    val blockCount: Int,
    @Json(name = "emptyBlocks")
    val emptyBlocks: Int,
    @Json(name = "link")
    val link: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "poolId")
    val poolId: Int,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "slug")
    val slug: String
)