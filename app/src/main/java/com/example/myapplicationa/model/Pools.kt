package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pools(
    @Json(name = "pools")
    val pools: List<PoolDetailResponse>
)