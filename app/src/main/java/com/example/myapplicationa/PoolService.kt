package com.example.myapplicationa

import com.example.myapplicationa.model.PoolWrapper
import com.example.myapplicationa.model.Pools
import com.example.myapplicationa.model.PoolsHashrateItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PoolService {
    @GET("v1/mining/pool/{slug}")
    fun getPoolByName(
        @Path("slug") slug: String,
    ): Call<PoolWrapper>
    @GET("v1/mining/pools/1y")
    fun getPools(): Call<Pools>
    @GET("v1/mining/hashrate/pools/1w")
    fun getHashrate(): Call<List<PoolsHashrateItem>>
}