package com.grappim.cashier.ui.root

import com.grappim.navigation.router.ActivityRouter
import com.grappim.core.MainViewModel
import com.grappim.extensions.Timer
import com.grappim.workers.WorkerHelper
import javax.inject.Inject

class MainViewModelImpl @Inject constructor(
    private val workerHelper: WorkerHelper,
    private val activityRouter: ActivityRouter
) : MainViewModel() {

    private val timer: Timer by lazy {
        Timer(
            runAtStart = true,
            onTick = {
                doWorkOnTick()
            }
        )
    }

    init {

    }

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