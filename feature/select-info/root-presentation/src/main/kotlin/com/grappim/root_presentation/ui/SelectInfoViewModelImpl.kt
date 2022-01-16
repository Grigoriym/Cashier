package com.grappim.root_presentation.ui

import com.grappim.core.SingleLiveEvent
import com.grappim.select_info.common_navigation.SelectInfoFlowScreenNavigator
import com.grappim.select_info.common_navigation.SelectInfoRootFlow
import com.grappim.select_info.common_navigation.SelectInfoViewModel
import javax.inject.Inject

class SelectInfoViewModelImpl @Inject constructor(
    private val selectInfoFlowScreenNavigator: SelectInfoFlowScreenNavigator
) : SelectInfoViewModel() {

    override val nextScreen = SingleLiveEvent<SelectInfoRootFlow>()

    override fun setNextScreen(position: Int) {
        nextScreen.value = SelectInfoRootFlow.values()[position]
    }

    override fun goToSelectCashBox() {
        nextScreen.value = SelectInfoRootFlow.SELECT_CASH_BOX
    }

    override fun goBack() {
        selectInfoFlowScreenNavigator.goBack()
    }

}