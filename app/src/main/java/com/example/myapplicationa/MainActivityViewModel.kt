package com.example.myapplicationa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationa.MempoolDataSource.getPoolByName
import com.example.myapplicationa.model.PoolWrapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.ForkJoinTask.getPool

class MainActivityViewModel: ViewModel() {
    private val _screenState = MutableLiveData<MainScreenState>()
    val screenState: LiveData<MainScreenState> = _screenState

    init {
        _screenState.value = MainScreenState.Loading

        getPoolByName().enqueue(object : Callback<PoolWrapper> {

            override fun onResponse(call: Call<PoolWrapper>, response: Response<PoolWrapper>) {
                _screenState.value = MainScreenState.Success(response.body()!!)
            }

            override fun onFailure(call: Call<PoolWrapper>, t: Throwable) {
                t.printStackTrace()
                _screenState.value = MainScreenState.Error(t)
            }
        })
    }
}