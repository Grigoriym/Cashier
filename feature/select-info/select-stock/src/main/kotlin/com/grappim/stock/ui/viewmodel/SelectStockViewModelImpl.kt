package com.grappim.stock.ui.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.viewModelScope
import com.grappim.domain.interactor.stock.getStocks.GetStocksUseCase
import com.grappim.domain.interactor.stock.saveStock.SaveStockParams
import com.grappim.domain.interactor.stock.saveStock.SaveStockUseCase
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.stock.model.StockProgressItem
import com.grappim.uikit.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UnusedPrivateProperty")
class SelectStockViewModelImpl @Inject constructor(
    private val getStocksUseCase: GetStocksUseCase,
    private val saveStockUseCase: SaveStockUseCase,
    private val selectStockLocalRepository: SelectStockLocalRepository,
    private val generalStorage: GeneralStorage
) : SelectStockViewModel() {

    override val stockProgresses: List<StockProgressItem> = getStockProgressItems()

    override val selectedStock =
        MutableStateFlow<Stock?>(selectStockLocalRepository.getSelectedStock())
    override val stocksResult = MutableStateFlow<List<Stock>>(emptyList())

    init {
        getStocks()
    }

    override fun selectStock(stock: Stock) {
        selectStockLocalRepository.setSelectedStock(stock)
        selectedStock.value = selectStockLocalRepository.getSelectedStock()
    }

    override fun saveStock() {
        viewModelScope.launch {
            val stockToSave = requireNotNull(selectStockLocalRepository.getSelectedStock()) {
                "Stock must not be null"
            }
            saveStockUseCase.execute(SaveStockParams(stockToSave))
        }
    }

    @MainThread
    override fun getStocks() {
//        viewModelScope.launch {
//            _loading.value = true
//            val result = getStocksUseCase.execute()
//            _loading.value = false
//            when (result) {
//                is Try.Error -> {
//                    _error.value = result.result
//                }
//
//                is Try.Success -> {
//                    stocksResult.value = result.result
//                }
//            }
//        }
    }

    private fun getStockProgressItems(): List<StockProgressItem> = listOf(
        StockProgressItem(R.string.outlet_selecting, true),
        StockProgressItem(R.string.outlet_checkout, false)
    )

    override fun onCleared() {
        selectStockLocalRepository.clear()
        super.onCleared()
    }

    override fun clearData() {
        viewModelScope.launch {
            generalStorage.clearData()
        }
    }
}
