package com.example.myapplicationa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplicationa.MempoolDataSource.getPoolByName
import com.example.myapplicationa.model.PoolWrapper
import com.example.myapplicationa.pooldetail.PoolDetailFragment
import com.example.myapplicationa.pooldetail.PoolDetailFragmentArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoolDetailViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val slug = PoolDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).slug
    private val _screenState = MutableLiveData<PoolScreenState>()
    val screenState: LiveData<PoolScreenState> = _screenState

    init {
        _screenState.value = PoolScreenState.Loading

        getPoolByName(slug).enqueue(object : Callback<PoolWrapper> {

            override fun onResponse(call: Call<PoolWrapper>, response: Response<PoolWrapper>) {
                _screenState.value = PoolScreenState.Success(response.body()!!)
            }

            override fun onFailure(call: Call<PoolWrapper>, t: Throwable) {
                t.printStackTrace()
                _screenState.value = PoolScreenState.Error(t)
            }
        })
    }
}