package com.grappim.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * The idea was taken from here: https://stackoverflow.com/a/70776160/9822532
 */
class Timer(
    private val countDownInterval: Long = 30_000L,
    runAtStart: Boolean = false,
    private val onFinish: (() -> Unit)? = null,
    private val onTick: ((Long) -> Unit)? = null
) {
    private var job: Job = Job()

    private val _playerMode = MutableStateFlow(PlayerMode.STOPPED)
    private val playerMode = _playerMode.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        if (runAtStart) start()
    }

    fun start() {
        job.cancel()
        job = scope.launch(Dispatchers.IO) {
            _playerMode.value = PlayerMode.PLAYING
            while (isActive) {
                onTick?.invoke(countDownInterval)
                delay(timeMillis = countDownInterval)
            }
        }
    }

    fun pause() {
        job.cancel()
        _playerMode.value = PlayerMode.PAUSED
    }

    fun stop() {
        job.cancel()
        _playerMode.value = PlayerMode.STOPPED
    }

    private enum class PlayerMode {
        PLAYING,
        PAUSED,
        STOPPED
    }
}
