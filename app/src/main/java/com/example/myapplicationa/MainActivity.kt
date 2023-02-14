package com.example.myapplicationa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
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
    val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.screenState.observe(this) { state ->
            when(state){
                is MainScreenState.Error -> binding.textPoolName.text = state.throwable.localizedMessage
                is MainScreenState.Loading -> "Loading..."
                is MainScreenState.Success -> {
                    binding.textPoolName.text = state.data.pool.name
                    binding.textPoolLink.text = state.data.pool.link
                    binding.textBlockCountAll.text = state.data.blockCount.all.toString()
                    binding.textBlockCount24h.text = state.data.blockCount.h.toString()
                    binding.textBlockCount1w.text = state.data.blockCount.w.toString()
                    binding.textBlockShareAll.text = state.data.blockShare.all.toString()
                    binding.textBlockShare24h.text = state.data.blockShare.h.toString()
                    binding.textBlockShare1w.text = state.data.blockShare.w.toString()
                    binding.textEstimatedHashrate.text = state.data.estimatedHashrate
                }
            }
        }
    }
}