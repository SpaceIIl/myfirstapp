package com.example.myapplicationa.latestTransactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationa.MempoolDataSource.getTransactions
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {

    private val _screenState = MutableLiveData<TransactionsScreenState>()
    val screenState: LiveData<TransactionsScreenState> = _screenState

    init {
        loadData()
    }

    private fun loadData() {
        _screenState.value = TransactionsScreenState.Loading

        viewModelScope.launch {
            try {
                val transaction = getTransactions().body()!!
                _screenState.postValue(TransactionsScreenState.Success(transaction))
            } catch (exception: Exception) {
                _screenState.postValue(TransactionsScreenState.Error(Throwable()))
            }
        }
    }

    fun retryLoadingData() {
        loadData()
    }
}