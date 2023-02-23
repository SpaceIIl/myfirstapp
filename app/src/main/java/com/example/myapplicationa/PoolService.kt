package com.example.myapplicationa

import com.example.myapplicationa.model.Pool
import com.example.myapplicationa.model.PoolWrapper
import com.example.myapplicationa.model.Pools
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PoolService {
    @GET("v1/mining/pool/Antpool")
    fun getPoolByName(): Call<PoolWrapper>
    @GET("v1/mining/pools/1w")
    fun getPools(): Call<Pools>
}