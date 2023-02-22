package com.example.myapplicationa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationa.MempoolDataSource.getPoolByName
import com.example.myapplicationa.model.PoolWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelAntpool: ViewModel() {
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