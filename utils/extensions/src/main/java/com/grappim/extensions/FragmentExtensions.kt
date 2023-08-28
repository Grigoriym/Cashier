package com.grappim.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.showToast(message: String?, long: Boolean = true) =
    requireContext().showToast(message, long)

fun Fragment.getErrorMessage(throwable: Throwable): String =
    requireContext().getErrorMessage(throwable)

/**
 * Launches a new coroutine and repeats `block` every time the Fragment's viewLifecycleOwner
 * is in and out of `minActiveState` lifecycle state.
 */
inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.assistedViewModel(
    crossinline viewModelProducer: (SavedStateHandle) -> T
) = viewModels<T> {
    object : AbstractSavedStateViewModelFactory(this@assistedViewModel, arguments) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T = viewModelProducer(handle) as T
    }
}
