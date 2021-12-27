package com.grappim.cashier.ui.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.grappim.extensions.interval
import com.grappim.navigation.Navigator
import com.grappim.workers.WorkerHelper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val workerHelper: WorkerHelper,
    private val navigator: Navigator
) : ViewModel() {

    private val sync = interval(15, TimeUnit.SECONDS)
        .onStart {
            workerHelper.startMainWorkers()
        }
        .onEach {
            workerHelper.startMainWorkers()
        }
        .launchIn(viewModelScope)

    fun startSync() {
        viewModelScope.launch {
            if (!sync.isActive) {
                sync.start()
            }
        }
    }

    fun stopSync() {
        viewModelScope.launch {
            sync.cancel()
        }
    }
}