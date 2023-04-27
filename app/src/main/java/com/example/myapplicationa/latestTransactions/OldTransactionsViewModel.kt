package com.example.myapplicationa.latestTransactions/*

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationa.MempoolDataSource.getTransactions
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class TransactionsViewModel: ViewModel() {
    private val _screenState = MutableLiveData<TransactionsScreenState>()
    val screenState: LiveData<TransactionsScreenState> = _screenState

    init {
        startPeriodicRefresh(FetchDelay)
    }

    @ExperimentalTime
    private fun startPeriodicRefresh(refreshInterval: Duration) {
        viewModelScope.launch {
            while (isActive) {
                loadData()
                delay(refreshInterval)
            }
        }
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

    companion object {
        private val FetchDelay = 10.seconds
    }
}
*/
