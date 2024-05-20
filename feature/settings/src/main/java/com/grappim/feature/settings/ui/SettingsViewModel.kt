package com.grappim.feature.settings.ui

import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.base.BaseViewModel
import com.grappim.domain.model.biometrics.BiometricsStatus
import com.grappim.domain.storage.GeneralStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val generalStorage: GeneralStorage) :
    BaseViewModel() {

    val biometricStatus: Flow<Boolean>
        get() = generalStorage.biometricsStatus
            .map {
                when (it) {
                    BiometricsStatus.NOT_SET,
                    BiometricsStatus.REFUSED -> false

                    BiometricsStatus.SET -> true
                }
            }

    fun onGithubSrcClicked() {
        flowRouter.goToGithubSrc()
    }

    fun setUseBiometrics(isChecked: Boolean) {
        viewModelScope.launch {
            val status = if (isChecked) {
                BiometricsStatus.SET
            } else {
                BiometricsStatus.NOT_SET
            }
            generalStorage.setBiometricsStatus(status)
        }
    }
}
