package com.grappim.domain.model.waybill

enum class WaybillStatus(val value: String) {
    ACTIVE("active"),
    DRAFT("draft");

    companion object {
        fun getStatusByValue(value: String): WaybillStatus =
            when (value) {
                ACTIVE.value -> ACTIVE
                DRAFT.value -> DRAFT
                else -> throw IllegalArgumentException("incorrect status $value")
            }
    }
}