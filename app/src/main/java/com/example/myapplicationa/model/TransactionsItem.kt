package com.example.myapplicationa.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionsItem(
    @Json(name = "fee")
    val fee: Int,
    @Json(name = "txid")
    val txid: String,
    @Json(name = "value")
    val value: Int,
    @Json(name = "vsize")
    val vsize: Int
)