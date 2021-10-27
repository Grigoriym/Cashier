package com.grappim.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.extensions.interval
import com.grappim.myapplication.WorkerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workerHelper: WorkerHelper
) : ViewModel() {

//    private val sync = interval(15, TimeUnit.SECONDS)
//        .onStart {
//            workerHelper.startMainWorkers()
//        }
//        .onEach {
//            workerHelper.startMainWorkers()
//        }
//        .launchIn(viewModelScope)

    fun startSync() {
//        viewModelScope.launch {
//            if (!sync.isActive) {
//                sync.start()
//            }
//        }
    }

    fun stopSync() {
//        viewModelScope.launch {
//            sync.cancel()
//        }
    }
}