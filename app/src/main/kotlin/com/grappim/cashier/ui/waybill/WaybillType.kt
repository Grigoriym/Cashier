package com.grappim.cashier.ui.waybill

enum class WaybillType(val value: String) {
    INWAYBILL("inwaybill"),
    OUTWAYBILL("outwaybill");

    companion object {
        fun getTypeByValue(value: String): WaybillType =
            when (value) {
                INWAYBILL.value -> INWAYBILL
                OUTWAYBILL.value -> OUTWAYBILL
                else -> throw IllegalArgumentException("incorrect status $value")
            }
    }

}