package com.grappim.domain.model

data class FeatureToggle(
    val merchantId: String,
    val stockId: String,
    val isWaybillEnabled: Boolean,
    val isSalesEnabled: Boolean
) {
    companion object {
        fun empty(): FeatureToggle =
            FeatureToggle(
                merchantId = "",
                stockId = "",
                isWaybillEnabled = false,
                isSalesEnabled = false
            )
    }
}