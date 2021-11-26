package com.grappim.domain.repository.local

import com.grappim.domain.model.waybill.Waybill
import kotlinx.coroutines.flow.Flow

interface WaybillLocalRepository {

    val waybill: Waybill

    val waybillFlow: Flow<Waybill>

    fun setWaybill(waybill: Waybill)

    fun setComment(text: String)

    fun setActualDate(text: String)

    fun clear()

}