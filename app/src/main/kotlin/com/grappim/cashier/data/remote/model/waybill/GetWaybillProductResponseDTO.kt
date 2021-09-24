package com.grappim.cashier.data.remote.model.waybill

data class GetWaybillProductResponseDTO(
    val product: WaybillProductDTO,
    val found: Boolean
)