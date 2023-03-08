package com.example.myapplicationa.poolsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationa.MempoolDataSource.getPools
import com.example.myapplicationa.model.Pools
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoolsViewModel: ViewModel() {
    private val _screenState = MutableLiveData<PoolsScreenState>()
    val screenState: LiveData<PoolsScreenState> = _screenState

    init {
        _screenState.value = PoolsScreenState.Loading

        getPools().enqueue(object : Callback<Pools> {

            override fun onResponse(call: Call<Pools>, response: Response<Pools>) {
                _screenState.postValue(PoolsScreenState.Success(response.body()!!.pools))
            }

            override fun onFailure(call: Call<Pools>, t: Throwable) {
                t.printStackTrace()
                _screenState.postValue(PoolsScreenState.Error(t))
            }
        })
    }
}