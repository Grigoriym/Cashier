package com.grappim.cashier.ui.root

import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.MainViewModel
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.cashier.data.workersapi.WorkerHelper
import com.grappim.domain.storage.GeneralStorage
import com.grappim.extensions.Timer
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainViewModelImpl @Inject constructor(
    private val workerHelper: WorkerHelper,
    generalStorage: GeneralStorage
) : MainViewModel() {

    private val timer: Timer by lazy {
        Timer(
            runAtStart = true,
            onTick = {
                doWorkOnTick()
            }
        )
    }

    override val isAuthError: StateFlow<Boolean> = generalStorage
        .authErrorFlow
        .stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = false
        )

    override fun goToAuth() {
        activityRouter.goToAuth()
    }

    private fun doWorkOnTick() {
        workerHelper.startMainWorkers()
    }

    override fun startSync() {
        timer.start()
    }

    override fun restartSync() {
        timer.stop()
        timer.start()
    }

    override fun stopSync() {
        timer.stop()
    }
}
