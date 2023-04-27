package com.example.myapplicationa.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationa.MempoolDataSource.getHashrate
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {
    private val _screenState = MutableLiveData<HomeScreenScreenState>()
    val screenState: LiveData<HomeScreenScreenState> = _screenState

    init {
        loadData()
    }

    private fun loadData() {
        _screenState.value = HomeScreenScreenState.Loading

        viewModelScope.launch {
            try {
                val home = getHashrate().body()!!
                _screenState.postValue(HomeScreenScreenState.Success(home))
            } catch (exception: Exception) {
                _screenState.postValue(HomeScreenScreenState.Error(Throwable()))
            }
        }
    }

    fun retryLoadingData() {
        loadData()
    }
}
