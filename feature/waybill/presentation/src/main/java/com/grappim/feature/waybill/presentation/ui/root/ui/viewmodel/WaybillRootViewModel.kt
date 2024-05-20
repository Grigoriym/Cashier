package com.grappim.feature.waybill.presentation.ui.root.ui.viewmodel

import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.feature.waybill.domain.model.Waybill
import kotlinx.coroutines.flow.StateFlow

abstract class WaybillRootViewModel : BaseViewModel() {

    abstract val waybillFlow: StateFlow<Waybill>
}
