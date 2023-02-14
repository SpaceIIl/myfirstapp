package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PoolWrapper(
    @Json(name = "blockCount")
    val blockCount: BlockCount,
    @Json(name = "blockShare")
    val blockShare: BlockShare,
    @Json(name = "estimatedHashrate")
    val estimatedHashrate: String,
    @Json(name = "pool")
    val pool: Pool,
    @Json(name = "reportedHashrate")
    val reportedHashrate: Any?
)