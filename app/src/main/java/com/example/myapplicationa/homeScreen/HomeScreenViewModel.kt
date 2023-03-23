package com.example.myapplicationa.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationa.MempoolDataSource.getHashrate
import com.example.myapplicationa.model.PoolsHashrateItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenViewModel: ViewModel() {
    private val _screenState = MutableLiveData<HomeScreenScreenState>()
    val screenState: LiveData<HomeScreenScreenState> = _screenState

    init {
        _screenState.value = HomeScreenScreenState.Loading

        getHashrate().enqueue(object : Callback<List<PoolsHashrateItem>> {

            override fun onResponse(call: Call<List<PoolsHashrateItem>>, response: Response<List<PoolsHashrateItem>>) {
                _screenState.postValue(HomeScreenScreenState.Success(response.body()!!))
            }

            override fun onFailure(call: Call<List<PoolsHashrateItem>>, t: Throwable) {
                t.printStackTrace()
                _screenState.postValue(HomeScreenScreenState.Error(t))
            }
        })
    }
}