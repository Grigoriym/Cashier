package com.grappim.feature.select_info.root_presentation.ui

import com.grappim.core.SingleLiveEvent
import com.grappim.feature.select_info.common_navigation.SelectInfoRootFlow
import com.grappim.feature.select_info.common_navigation.SelectInfoViewModel
import javax.inject.Inject

class SelectInfoViewModelImpl @Inject constructor(

) : SelectInfoViewModel() {

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
