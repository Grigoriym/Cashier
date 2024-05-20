package com.grappim.feature.selectinfo.rootpresentation.ui

import com.grappim.cashier.core.SingleLiveEvent
import com.grappim.feature.selectinfo.commonnavigation.SelectInfoRootFlow
import com.grappim.feature.selectinfo.commonnavigation.SelectInfoViewModel
import javax.inject.Inject

class SelectInfoViewModelImpl @Inject constructor() : SelectInfoViewModel() {

    override val nextScreen = SingleLiveEvent<SelectInfoRootFlow>()

    override fun setNextScreen(position: Int) {
        nextScreen.value = SelectInfoRootFlow.values()[position]
    }

    override fun goToSelectCashBox() {
        nextScreen.value = SelectInfoRootFlow.SELECT_CASH_BOX
    }

    override fun backToSelectStock() {
        nextScreen.value = SelectInfoRootFlow.SELECT_STOCK
    }

    override fun goBack() {
        flowRouter.onBackPressed()
    }
}
