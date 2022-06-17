package com.grappim.feature.waybill.presentation.ui.root.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.repository.WaybillLocalRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class WaybillRootViewModelImpl @Inject constructor(
    private val waybillLocalRepository: WaybillLocalRepository
) : WaybillRootViewModel() {

    override val waybillFlow: StateFlow<Waybill>
        get() = waybillLocalRepository.waybillFlow
            .stateIn(
                scope = viewModelScope,
                started = WhileViewSubscribed,
                initialValue = waybillLocalRepository.waybill
            )

    override fun onCleared() {
        waybillLocalRepository.clear()
        super.onCleared()
    }
}