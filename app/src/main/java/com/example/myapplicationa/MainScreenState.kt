package com.example.myapplicationa

import com.example.myapplicationa.model.PoolWrapper

sealed class MainScreenState {
    data class Success(val data: PoolWrapper) : MainScreenState()
    data class Error(val throwable: Throwable) : MainScreenState()
    object Loading : MainScreenState()
}