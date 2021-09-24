package com.grappim.cashier.core.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

interface CoroutineContextProvider {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val ui: CoroutineDispatcher
}

@Singleton
class CoroutineContextProviderImpl @Inject constructor() : CoroutineContextProvider {
    override val io = Dispatchers.IO
    override val default = Dispatchers.Default
    override val ui: CoroutineDispatcher = Dispatchers.Main
}