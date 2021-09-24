package com.grappim.cashier.domain.waybill

import com.grappim.cashier.data.remote.model.waybill.WaybillDTO

data class UpdateWaybillRequestDTO(
    private val waybill: WaybillDTO
)
