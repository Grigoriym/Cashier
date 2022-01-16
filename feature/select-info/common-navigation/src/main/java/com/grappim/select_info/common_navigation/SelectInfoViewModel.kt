package com.grappim.select_info.common_navigation

import androidx.lifecycle.LiveData
import com.grappim.core.BaseViewModel

abstract class SelectInfoViewModel : BaseViewModel() {

    abstract val nextScreen: LiveData<SelectInfoRootFlow>

    abstract fun setNextScreen(position: Int)

    abstract fun goBack()

    abstract fun goToSelectCashBox()

}