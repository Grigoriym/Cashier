package com.grappim.repository.remote

import com.grappim.cashier.common.di.AppScope
import com.grappim.domain.model.FeatureToggle
import com.grappim.domain.repository.FeatureToggleRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.network.api.FeatureToggleApi
import com.grappim.network.di.api.QualifierFeatureToggleApi
import com.grappim.network.mappers.toDomain
import javax.inject.Inject

@AppScope
class FeatureToggleRepositoryImpl @Inject constructor(
    private val generalStorage: GeneralStorage,
    @QualifierFeatureToggleApi private val featureToggleApi: FeatureToggleApi
) : FeatureToggleRepository {

    override suspend fun getFeatures(): FeatureToggle {
        val response = featureToggleApi.getFeatures(generalStorage.stockId)
        return response.toDomain()
    }
}
