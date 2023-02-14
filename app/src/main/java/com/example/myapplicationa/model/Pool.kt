package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pool(
    @Json(name = "addresses")
    val addresses: List<String>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "link")
    val link: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "regexes")
    val regexes: List<String>,
    @Json(name = "slug")
    val slug: String
)