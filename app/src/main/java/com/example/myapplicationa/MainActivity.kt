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
                is MainScreenState.Success -> bindPoolWrapper(state.data)
            }
        }
    }
    private fun bindPoolWrapper(poolWrapper: PoolWrapper) {
        with(binding) {
            textPoolName.text = poolWrapper.pool.name
            textPoolLink.text = poolWrapper.pool.link
            textBlockCount.text = getString(R.string.block_count)
            textBlockCountAll.text = getString(R.string.all, poolWrapper.blockCount.all.toString())
            textBlockCount24h.text = getString(R.string.h, poolWrapper.blockCount.h.toString())
            textBlockCount1w.text = getString(R.string.w, poolWrapper.blockCount.w.toString())
            textBlockShare.text = getString(R.string.block_share)
            textBlockShareAll.text = getString(R.string.all, poolWrapper.blockShare.all.toString())
            textBlockShare24h.text = getString(R.string.h, poolWrapper.blockShare.h.toString())
            textBlockShare1w.text = getString(R.string.w, poolWrapper.blockShare.w.toString())
            textEstimatedHashrate.text = getString(R.string.estimated_hashrate)
            textEstimatedHashrateCount.text = poolWrapper.estimatedHashrate
        }
    }
}