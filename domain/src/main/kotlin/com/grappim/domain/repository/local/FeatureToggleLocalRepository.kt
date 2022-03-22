package com.grappim.domain.repository.local

import com.grappim.domain.model.FeatureToggle
import kotlinx.coroutines.flow.Flow

interface FeatureToggleLocalRepository {

    val featureToggleData: Flow<FeatureToggle>

    suspend fun updateDataStore(featureToggle: FeatureToggle)

}