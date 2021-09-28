package com.grappim.db.converter

import androidx.room.TypeConverter
import com.grappim.domain.model.waybill.WaybillStatus

class AcceptanceStatusConverter {

    @TypeConverter
    fun unitToString(status: WaybillStatus): String =
        status.value

    @TypeConverter
    fun stringToUnit(value: String): WaybillStatus =
        WaybillStatus.getStatusByValue(value)
}