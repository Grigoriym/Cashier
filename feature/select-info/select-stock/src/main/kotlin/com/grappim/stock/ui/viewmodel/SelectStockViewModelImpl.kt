package com.grappim.stock.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.grappim.common.lce.Try
import com.grappim.common.lce.withoutParams
import com.grappim.domain.interactor.outlet.GetStocksUseCase
import com.grappim.domain.interactor.outlet.SaveStockInfoUseCase
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.logger.logD
import com.grappim.select_info.common_navigation.SelectInfoFlowScreenNavigator
import com.grappim.stock.R
import com.grappim.stock.model.StockProgressItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectStockViewModelImpl @Inject constructor(
    private val getStocksUseCase: GetStocksUseCase,
    private val saveStockInfoUseCase: SaveStockInfoUseCase,
    private val selectStockLocalRepository: SelectStockLocalRepository,
    private val selectInfoFlowScreenNavigator: SelectInfoFlowScreenNavigator
) : SelectStockViewModel() {

    override val stockProgresses: List<StockProgressItem> = getStockProgressItems()

    override val selectedStock =
        MutableStateFlow<Stock?>(selectStockLocalRepository.getSelectedStock())
    override val stocksResult = MutableStateFlow<List<Stock>>(emptyList())

    init {
        getStocks()
    }

    override fun onBackPressed() {
        selectInfoFlowScreenNavigator.activityOnBackPressed()
    }

    override fun selectStock(stock: Stock) {
        selectStockLocalRepository.setSelectedStock(stock)
        selectedStock.value = selectStockLocalRepository.getSelectedStock()
    }

    override fun showSelectInfo() {
//        selectInfoFlowScreenNavigator.goToSelectCashBox()
    }

    override fun saveStock() {
        viewModelScope.launch {
            val stockToSave = requireNotNull(selectStockLocalRepository.getSelectedStock()) {
                "Stock must not be null"
            }
            saveStockInfoUseCase.invoke(SaveStockInfoUseCase.Params(stockToSave))
        }
    }

    @MainThread
    override fun getStocks() {
        viewModelScope.launch {
            getStocksUseCase(withoutParams())
                .collect {
                    _loading.value = it is Try.Loading
                    when (it) {
                        is Try.Error -> {
                            _error.value = it.exception
                        }
                        is Try.Success -> {
                            stocksResult.value = it.data
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
        logD("${this} viewModel onCleared")
        selectStockLocalRepository.clear()
        super.onCleared()
    }
}