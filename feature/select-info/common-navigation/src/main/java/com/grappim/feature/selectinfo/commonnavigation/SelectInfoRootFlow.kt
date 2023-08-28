package com.grappim.feature.selectinfo.commonnavigation

enum class SelectInfoRootFlow {

    SELECT_STOCK,
    SELECT_CASH_BOX;

    companion object {
        fun getAdapterSize(): Int = values().size
    }
}
