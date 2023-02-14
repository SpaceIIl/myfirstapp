package com.example.myapplicationa

import com.example.myapplicationa.model.Pool
import com.example.myapplicationa.model.PoolWrapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
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

    fun getPoolByName(): Call<PoolWrapper> {
        return poolService.getPoolByName()
    }
}