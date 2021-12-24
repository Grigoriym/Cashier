package com.grappim.stock

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.grappim.core.BaseViewModel
import com.grappim.domain.base.Try
import com.grappim.domain.base.withoutParams
import com.grappim.domain.interactor.outlet.GetStocksUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectStockViewModel @Inject constructor(
    private val getStocksUseCase: GetStocksUseCase,
    private val saveStockInfoUseCase: SaveStockInfoUseCase,
    private val navigator: Navigator,
    private val selectStockLocalRepository: SelectStockLocalRepository
) : BaseViewModel() {

    val stockProgresses: List<StockProgressItem> = getStockProgressItems()

    private val _selectedStock =
        MutableStateFlow<Stock?>(selectStockLocalRepository.getSelectedStock())
    val selectedStock: StateFlow<Stock?>
        get() = _selectedStock

    private val _stocksResult = MutableStateFlow<List<Stock>>(emptyList())
    val stocksResult: StateFlow<List<Stock>>
        get() = _stocksResult.asStateFlow()

    init {
        getStocks()
    }

    fun onBackPressed() {
        navigator.popBackStack()
    }

    fun selectStock(stock: Stock) {
        selectStockLocalRepository.setSelectedStock(stock)
        _selectedStock.value = selectStockLocalRepository.getSelectedStock()
    }

    fun showSelectInfo() {
        navigator.navigateToFlow(NavigationFlow.SelectInfoCashierFlow)
    }

    fun saveStock() {
        viewModelScope.launch {
            val stockToSave = requireNotNull(selectStockLocalRepository.getSelectedStock()) {
                "Stock must not be null"
            }
            saveStockInfoUseCase.invoke(SaveStockInfoUseCase.Params(stockToSave))
        }
    }

    @MainThread
    fun getStocks() {
        viewModelScope.launch {
            getStocksUseCase(withoutParams())
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                        is Try.Success -> {
                            _stocksResult.value = it.data
                        }
                    }
                }
        }
    }

    private fun getStockProgressItems(): List<StockProgressItem> =
        listOf(
            StockProgressItem(R.string.outlet_selecting, true),
            StockProgressItem(R.string.outlet_checkout, false)
        )

    override fun onCleared() {
        selectStockLocalRepository.clear()
        super.onCleared()
    }
}