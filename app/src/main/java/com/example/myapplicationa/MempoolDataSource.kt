package com.example.myapplicationa

import com.example.myapplicationa.model.PoolWrapper
import com.example.myapplicationa.model.Pools
import com.example.myapplicationa.model.PoolsHashrateItem
import com.example.myapplicationa.model.TransactionsItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MempoolDataSource {
    private val poolService by lazy {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://mempool.space/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        retrofit.create(PoolService::class.java)
    }

    suspend fun getPoolByName(slug: String): Response<PoolWrapper> {
        return poolService.getPoolByName(slug)
    }

    suspend fun getPools(): Response<Pools> {
        return poolService.getPools()
    }

    suspend fun getHashrate(): Response<List<PoolsHashrateItem>> {
        return poolService.getHashrate()
    }

    suspend fun getTransactions(): Response<List<TransactionsItem>> {
        return poolService.getTransactions()
    }
}