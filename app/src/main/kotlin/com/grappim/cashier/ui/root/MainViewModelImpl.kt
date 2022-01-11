package com.grappim.cashier.ui.root

import androidx.lifecycle.viewModelScope
import com.grappim.core.MainViewModel
import com.grappim.extensions.interval
import com.grappim.workers.WorkerHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModelImpl @Inject constructor(
    private val workerHelper: WorkerHelper
) : MainViewModel() {

    private val sync = interval(15, TimeUnit.SECONDS)
        .onStart {
            workerHelper.startMainWorkers()
        }
        .onEach {
            workerHelper.startMainWorkers()
        }
        .launchIn(viewModelScope)

    override fun startSync() {
        viewModelScope.launch {
            if (!sync.isActive) {
                sync.start()
            }
        }
    }

    override fun stopSync() {
        viewModelScope.launch {
            sync.cancel()
        }
    }
}