package com.grappim.products.root.ui

import androidx.lifecycle.LiveData
import com.grappim.core.SingleLiveEvent
import com.grappim.core.base.BaseViewModel2
import javax.inject.Inject

class ProductsRootViewModel @Inject constructor(

) : BaseViewModel2() {

    private val _scannedBarcode = SingleLiveEvent<String>()
    val scannedBarcode: LiveData<String>
        get() = _scannedBarcode

    fun setBarcode(barcode: String) {
        _scannedBarcode.value = barcode
    }

}