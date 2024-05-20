package com.grappim.repository.utils

import com.grappim.domain.model.FeatureToggle
import com.grappim.repository.model.datastore.FeatureToggleProto

fun FeatureToggleProto.toDomain(): FeatureToggle = FeatureToggle(
    merchantId = this.merchantId,
    stockId = this.stockId,
    isWaybillEnabled = this.isWaybillEnabled,
    isSalesEnabled = this.isSalesEnabled
)
