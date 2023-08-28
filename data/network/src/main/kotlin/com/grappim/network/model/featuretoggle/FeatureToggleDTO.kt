package com.grappim.network.model.featuretoggle

import kotlinx.serialization.Serializable

@Serializable
data class FeatureToggleDTO(
    val merchantId: String,
    val stockId: String,
    val isWaybillEnabled: Boolean,
    val isSalesEnabled: Boolean
)
