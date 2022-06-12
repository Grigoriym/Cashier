package com.grappim.waybill.ui.root.ui.viewmodel

import com.grappim.core.base.BaseViewModel
import com.grappim.domain.model.waybill.Waybill
import kotlinx.coroutines.flow.StateFlow

abstract class WaybillRootViewModel : BaseViewModel() {

    abstract val waybillFlow: StateFlow<Waybill>
}