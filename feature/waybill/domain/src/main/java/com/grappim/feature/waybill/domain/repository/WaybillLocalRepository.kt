package com.grappim.feature.waybill.domain.repository

import com.grappim.feature.waybill.domain.model.Waybill
import kotlinx.coroutines.flow.Flow

interface WaybillLocalRepository {

    val waybill: Waybill

    val waybillFlow: Flow<Waybill>

    fun setWaybill(waybill: Waybill)

    fun setComment(text: String)

    fun setActualDate(text: String)

    fun clear()

}