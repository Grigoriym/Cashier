package com.grappim.cashier.data.workersapi

interface WorkerHelper {
    fun startTokenRefresherWorker()

    fun stopMainWorkers()

    fun startMainWorkers()
}
