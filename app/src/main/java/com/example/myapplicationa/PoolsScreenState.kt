package com.example.myapplicationa

import com.example.myapplicationa.model.PoolX
import com.example.myapplicationa.model.Pools

sealed class PoolsScreenState {
    data class Success(val data: List<PoolX>) : PoolsScreenState()
    data class Error(val throwable: Throwable) : PoolsScreenState()
    object Loading : PoolsScreenState()
}
