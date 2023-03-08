package com.example.myapplicationa.poolsList

import com.example.myapplicationa.model.PoolDetailResponse

sealed class PoolsScreenState {
    data class Success(val data: List<PoolDetailResponse>) : PoolsScreenState()
    data class Error(val throwable: Throwable) : PoolsScreenState()
    object Loading : PoolsScreenState()
}
