package com.grappim.feature.waybill.domain.interactor.rollbackWaybill

import com.grappim.feature.waybill.domain.model.Waybill

data class RollbackWaybillParams(
    val waybill: Waybill
)