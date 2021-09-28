package com.grappim.network.model.waybill

data class GetWaybillProductResponseDTO(
    val product: WaybillProductDTO,
    val found: Boolean
)