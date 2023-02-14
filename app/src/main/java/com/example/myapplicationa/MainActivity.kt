package com.example.myapplicationa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapplicationa.databinding.ActivityMainBinding
import com.example.myapplicationa.model.PoolWrapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://mempool.space/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val poolService: PoolService = retrofit.create(PoolService::class.java)

        poolService.getPoolByName().enqueue(object : Callback<PoolWrapper> {

            override fun onResponse(call: Call<PoolWrapper>, response: Response<PoolWrapper>) {
                Log.i("MainActivity", response.toString())

                val body = response.body()!!
                val name = body.pool.name

                binding.Pool.text = name
            }

            override fun onFailure(call: Call<PoolWrapper>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null message")

                t.printStackTrace()
            }
        })
    }
}