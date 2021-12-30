package com.grappim.cashier.core.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import com.grappim.navigation.R

private fun buildDeepLink(
    destination: DeepLinkDestination
) = NavDeepLinkRequest.Builder
    .fromUri(destination.address.toUri())
    .build()

fun NavController.deepLinkNavigateTo(
    deepLinkDestination: DeepLinkDestination
) {
    val builder = NavOptions.Builder().apply {
        setPopEnterAnim(R.anim.pop_enter_from_left)
        setPopExitAnim(R.anim.pop_exit_to_right)

        setEnterAnim(R.anim.enter_from_right)
        setExitAnim(R.anim.exit_to_left)
    }

    if (deepLinkDestination.popUpTo) {
        builder.apply {
            setPopUpTo(
                destinationId = deepLinkDestination.destinationId,
                inclusive = true,
                saveState = true
            )
        }
    }

    navigate(
        buildDeepLink(deepLinkDestination),
        builder.build()
    )
}