package com.grappim.domain.repository

import com.grappim.domain.model.FeatureToggle

interface FeatureToggleRepository {

    suspend fun getFeatures(): FeatureToggle

}