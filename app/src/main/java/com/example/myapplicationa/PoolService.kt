package com.example.myapplicationa

import com.example.myapplicationa.model.PoolWrapper
import com.example.myapplicationa.model.Pools
import com.example.myapplicationa.model.PoolsHashrateItem
import com.example.myapplicationa.model.TransactionsItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PoolService {
    @GET("v1/mining/pool/{slug}")
    suspend fun getPoolByName(
        @Path("slug") slug: String,
    ): Response<PoolWrapper>
    @GET("v1/mining/pools/1y")
    suspend fun getPools(): Response<Pools>
    @GET("v1/mining/hashrate/pools/1w")
    suspend fun getHashrate(): Response<List<PoolsHashrateItem>>

    @GET("mempool/recent")
    suspend fun getTransactions(): Response<List<TransactionsItem>>
}