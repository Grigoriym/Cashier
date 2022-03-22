package com.grappim.network.model.feature_toggle

import kotlinx.serialization.Serializable

@Serializable
data class FeatureToggleDTO(
    val merchantId: String,
    val stockId: String,
    val isWaybillEnabled: Boolean,
    val isSalesEnabled: Boolean
)