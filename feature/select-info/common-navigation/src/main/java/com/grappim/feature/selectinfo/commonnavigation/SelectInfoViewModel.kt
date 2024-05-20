package com.grappim.feature.selectinfo.commonnavigation

import androidx.lifecycle.LiveData
import com.grappim.cashier.core.base.BaseViewModel

abstract class SelectInfoViewModel : BaseViewModel() {

    abstract val nextScreen: LiveData<SelectInfoRootFlow>

    abstract fun setNextScreen(position: Int)

    abstract fun goBack()

    abstract fun goToSelectCashBox()

    abstract fun backToSelectStock()
}
