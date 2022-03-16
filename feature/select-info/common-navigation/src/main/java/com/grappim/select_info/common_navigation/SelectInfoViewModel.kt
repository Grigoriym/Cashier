package com.grappim.select_info.common_navigation

import androidx.lifecycle.LiveData
import com.grappim.core.base.BaseViewModel
import com.grappim.core.base.BaseViewModel2

abstract class SelectInfoViewModel : BaseViewModel2() {

    abstract val nextScreen: LiveData<SelectInfoRootFlow>

    abstract fun setNextScreen(position: Int)

    abstract fun goBack()

    abstract fun goToSelectCashBox()

    abstract fun backToSelectStock()

}