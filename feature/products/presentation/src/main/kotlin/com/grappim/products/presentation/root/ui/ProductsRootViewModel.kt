package com.grappim.products.presentation.root.ui

import androidx.lifecycle.LiveData
import com.grappim.core.SingleLiveEvent
import com.grappim.core.base.BaseViewModel
import javax.inject.Inject

class ProductsRootViewModel @Inject constructor(

) : BaseViewModel() {

    private val _scannedBarcode = SingleLiveEvent<String>()
    val scannedBarcode: LiveData<String>
        get() = _scannedBarcode

    fun setBarcode(barcode: String) {
        _scannedBarcode.value = barcode
    }

}
