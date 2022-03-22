package com.grappim.network.api

import com.grappim.common.annotations.RequestWithAuthToken
import com.grappim.network.model.feature_toggle.FeatureToggleDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface FeatureToggleApi {

    @GET("feature")
    @RequestWithAuthToken
    suspend fun getFeatures(
        @Query("stockId") stockId: String
    ): FeatureToggleDTO
}