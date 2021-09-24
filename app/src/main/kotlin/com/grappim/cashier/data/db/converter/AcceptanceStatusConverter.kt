package com.grappim.cashier.data.db.converter

import androidx.room.TypeConverter
import com.grappim.cashier.ui.waybill.WaybillStatus

class AcceptanceStatusConverter {

    @TypeConverter
    fun unitToString(status: WaybillStatus): String =
        status.value

    @TypeConverter
    fun stringToUnit(value: String): WaybillStatus =
        WaybillStatus.getStatusByValue(value)
}