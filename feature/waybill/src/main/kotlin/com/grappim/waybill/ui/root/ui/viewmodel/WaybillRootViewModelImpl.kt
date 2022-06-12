package com.grappim.waybill.ui.root.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.grappim.core.functional.WhileViewSubscribed
import com.grappim.domain.model.waybill.Waybill
import com.grappim.domain.repository.local.WaybillLocalRepository
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