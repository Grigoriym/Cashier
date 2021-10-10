package com.grappim.core.delegate

fun <T> lazyUnsafe(
    initializer: () -> T
) = lazy(mode = LazyThreadSafetyMode.NONE) { initializer }