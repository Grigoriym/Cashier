package com.grappim.feature.auth.presentation.model

import com.grappim.core.resources.NativeText
import com.grappim.uikit.R

data class DevSnackbar(
    val show: Boolean,
    val text: NativeText
) {
    companion object {
        fun default() =
            DevSnackbar(
                show = false,
                text = NativeText.Empty
            )

        fun firstPhase(counter: Int) =
            DevSnackbar(
                show = true,
                text = NativeText.Arguments(
                    R.string.title_dev_counter,
                    listOf(counter)
                )
            )

        fun secondPhase() =
            DevSnackbar(
                show = true,
                text = NativeText.Resource(
                    id = R.string.title_dev_counter_finish
                )
            )
    }
}
