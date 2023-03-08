package com.example.myapplicationa.pooldetail

import com.example.myapplicationa.model.PoolWrapper

sealed class PoolScreenState {
    data class Success(val data: PoolWrapper) : PoolScreenState()
    data class Error(val throwable: Throwable) : PoolScreenState()
    object Loading : PoolScreenState()
}