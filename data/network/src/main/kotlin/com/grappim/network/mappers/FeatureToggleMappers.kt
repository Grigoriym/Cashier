package com.grappim.network.mappers

import com.grappim.domain.model.FeatureToggle
import com.grappim.network.model.feature_toggle.FeatureToggleDTO

fun FeatureToggleDTO.toDomain(): FeatureToggle =
    FeatureToggle(
        merchantId = merchantId,
        stockId = stockId,
        isWaybillEnabled = isWaybillEnabled,
        isSalesEnabled = isSalesEnabled
    )