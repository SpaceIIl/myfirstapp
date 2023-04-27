package com.example.myapplicationa.pooldetail

import androidx.lifecycle.*
import com.example.myapplicationa.MempoolDataSource
import com.example.myapplicationa.MempoolDataSource.getPoolByName
import com.example.myapplicationa.model.PoolWrapper
import com.example.myapplicationa.poolsList.PoolsScreenState
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PoolDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val slug = PoolDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).slug
    private val _screenState = MutableLiveData<PoolScreenState>()
    val screenState: LiveData<PoolScreenState> = _screenState

    init {
        loadData()
    }

    private fun loadData() {
        _screenState.value = PoolScreenState.Loading

        viewModelScope.launch {
            try {
                val pool = getPoolByName(slug).body()!!
                _screenState.postValue(PoolScreenState.Success(pool))
            }catch (exception: Exception){
                _screenState.postValue(PoolScreenState.Error(Throwable()))
            }
        }
    }

    fun retryLoadingData() {
        loadData()
    }
}