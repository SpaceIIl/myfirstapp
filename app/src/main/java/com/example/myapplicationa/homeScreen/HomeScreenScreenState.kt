package com.example.myapplicationa.homeScreen

import com.example.myapplicationa.model.PoolsHashrateItem

sealed class HomeScreenScreenState {
    data class Success(val data: List<PoolsHashrateItem>) : HomeScreenScreenState()
    data class Error(val throwable: Throwable) : HomeScreenScreenState()
    object Loading : HomeScreenScreenState()
}
