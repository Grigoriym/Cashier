package com.grappim.cashier.core.extensions

import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.grappim.cashier.R

fun ImageRequest.Builder.setStandardSettings() {
    transformations(CircleCropTransformation())
    crossfade(true)
    placeholder(R.drawable.ic_image_placeholder)
    error(R.drawable.ic_image_placeholder)
    fallback(R.drawable.ic_image_placeholder)
}