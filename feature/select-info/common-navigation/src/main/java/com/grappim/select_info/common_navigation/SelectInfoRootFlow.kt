package com.grappim.select_info.common_navigation

enum class SelectInfoRootFlow {

    SELECT_STOCK,
    SELECT_CASH_BOX;

    companion object {
        fun getAdapterSize(): Int = values().size
    }

}