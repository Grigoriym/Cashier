package com.grappim.domain.model.waybill

enum class WaybillStatus() {
    ACTIVE,
    DRAFT;

    companion object {
        fun getStatusByName(value: String): WaybillStatus =
            when (value) {
                ACTIVE.name -> ACTIVE
                DRAFT.name -> DRAFT
                else -> throw IllegalArgumentException("incorrect status $value")
            }
    }
}