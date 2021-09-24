package com.grappim.cashier.core.extensions

fun Int.padWithZeros(length: Int): String =
    this.toString().padStart(length, '0')