package com.example.myapplicationa.latestTransactions

import com.example.myapplicationa.model.TransactionsItem

sealed class TransactionsScreenState {
    data class Success(val data: List<TransactionsItem>) : TransactionsScreenState()
    data class Error(val throwable: Throwable) : TransactionsScreenState()
    object Loading : TransactionsScreenState()
}
