package com.grappim.repository.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.grappim.common.di.AppScope
import com.grappim.common.di.ApplicationContext
import com.grappim.domain.model.FeatureToggle
import com.grappim.domain.repository.local.FeatureToggleLocalRepository
import com.grappim.logger.logE
import com.grappim.repository.model.datastore.FeatureToggleProto
import com.grappim.repository.utils.FeatureToggleSerializer
import com.grappim.repository.utils.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@AppScope
class FeatureToggleLocalRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FeatureToggleLocalRepository {

    companion object {
        private const val DATA_STORE_FILE_NAME = "feature_toggle.pb"
    }

    private val Context.dataStore: DataStore<FeatureToggleProto> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = FeatureToggleSerializer
    )

    private val data: DataStore<FeatureToggleProto>
        get() = context.dataStore

    override val featureToggleData: Flow<FeatureToggle> = data.data
        .map {
            it.toDomain()
        }
        .catch { exception ->
            logE(exception)
            if (exception is IOException) {
                emit(FeatureToggleProto.getDefaultInstance().toDomain())
            } else {
                throw exception
            }
        }

    override suspend fun updateDataStore(featureToggle: FeatureToggle) {
        data.updateData { currentData ->
            currentData
                .toBuilder()
                .setMerchantId(featureToggle.merchantId)
                .setStockId(featureToggle.stockId)
                .setIsSalesEnabled(featureToggle.isSalesEnabled)
                .setIsWaybillEnabled(featureToggle.isWaybillEnabled)
                .build()
        }
    }

}