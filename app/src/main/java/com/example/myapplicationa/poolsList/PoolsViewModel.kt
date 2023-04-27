package com.example.myapplicationa.poolsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationa.MempoolDataSource.getPools
import com.example.myapplicationa.model.Pools
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoolsViewModel: ViewModel() {
    private val _screenState = MutableLiveData<PoolsScreenState>()
    val screenState: LiveData<PoolsScreenState> = _screenState

    init {
        loadData()
    }

    private fun loadData() {
        _screenState.value = PoolsScreenState.Loading

        viewModelScope.launch {
            try {
                val pools = getPools().body()!!.pools
                _screenState.postValue(PoolsScreenState.Success(pools))
            }catch (exception: Exception){
                _screenState.postValue(PoolsScreenState.Error(Throwable()))
            }
        }
    }

    fun retryLoadingData() {
        loadData()
    }
}